package org.example.bank.service;

import org.example.bank.dto.PaymentRequest;
import org.example.bank.model.Karta;
import org.example.bank.model.Konto;
import org.example.bank.model.Transakcja;
import org.example.bank.repository.KartaRepository;
import org.example.bank.repository.KontoRepository;
import org.example.bank.repository.TransakcjaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class PaymentService {
    private final KartaRepository kartaRepository;
    private final KontoRepository kontoRepository;
    private final TransakcjaRepository transakcjaRepository;

    public PaymentService(KartaRepository kartaRepository, KontoRepository kontoRepository, TransakcjaRepository transakcjaRepository) {
        this.kartaRepository = kartaRepository;
        this.kontoRepository = kontoRepository;
        this.transakcjaRepository = transakcjaRepository;
    }

    @Transactional
    public Map<String, Object> processPayment(PaymentRequest paymentRequest) {
        Map<String, Object> response = new HashMap<>();

        // Find card by number
        Optional<Karta> cardOptional = kartaRepository.findByNumerKarty(paymentRequest.getCardNumber());

        if (cardOptional.isEmpty()) {
            response.put("status", "failed");
            response.put("message", "Karta nie istnieje");
            return response;
        }

        Karta karta = cardOptional.get();

        // Validate expiry date
        LocalDate cardExpiryDate = karta.getTerminWaznosci();

        // Parse the expiry date from MM/YYYY format
        YearMonth providedExpiryDate;

        try {
            // Check if the year is 2 or 4 digits
            if (paymentRequest.getExpiryYear().length() == 2) {
                providedExpiryDate = YearMonth.of(
                        Integer.parseInt("20" + paymentRequest.getExpiryYear()),
                        Integer.parseInt(paymentRequest.getExpiryMonth())
                );
            } else {
                providedExpiryDate = YearMonth.of(
                        Integer.parseInt(paymentRequest.getExpiryYear()),
                        Integer.parseInt(paymentRequest.getExpiryMonth())
                );
            }
        } catch (NumberFormatException e) {
            response.put("status", "failed");
            response.put("message", "Nieprawidłowy format daty ważności");
            return response;
        }

        YearMonth cardExpiryYearMonth = YearMonth.from(cardExpiryDate);
        YearMonth currentMonth = YearMonth.now();

        // Check if the card has expired or if the provided date doesn't match
        if (!cardExpiryYearMonth.equals(providedExpiryDate) || cardExpiryYearMonth.isBefore(currentMonth)) {
            response.put("status", "failed");
            response.put("message", "Karta wygasła lub nieprawidłowa data ważności");
            return response;
        }
        if (paymentRequest.getCvv().length() != 3 ) {
            response.put("status", "failed");
            response.put("message", "CVV musi mieć 3 cyfry");
        } else if (!Objects.equals(karta.getCvv(), paymentRequest.getCvv())) {
            response.put("status", "failed");
            response.put("message", "Nieprawidłowy kod CVV");
            return response;
        }
        // Get the associated account
        Integer kontoId = karta.getKontoId();
        Optional<Konto> kontoOptional = kontoRepository.findById(kontoId);

        if (kontoOptional.isEmpty()) {
            response.put("status", "failed");
            response.put("message", "Nie znaleziono powiązanego konta");
            return response;
        }

        if (paymentRequest.getAmount().compareTo(karta.getLimitKarty()) > 0) {
            response.put("status", "failed");
            response.put("message", "Przekroczono limit karty");
            return response;
        }

        Konto konto = kontoOptional.get();
        BigDecimal amount = paymentRequest.getAmount();

        // Check if account has sufficient funds
        if (konto.getSaldo().compareTo(amount) < 0) {
            response.put("status", "failed");
            response.put("message", "Niewystarczające środki na koncie");
            return response;
        }

        // Deduct the amount from the account balance
        konto.setSaldo(konto.getSaldo().subtract(amount));
        kontoRepository.save(konto);

        // Create transaction record
        Transakcja transakcja = new Transakcja();
        transakcja.setKartaId(karta.getId());
        transakcja.setKwota(amount);
        transakcja.setData(LocalDateTime.now());
        transakcja.setTypTransakcji("ZAKUP");
        transakcjaRepository.save(transakcja);

        // Return success response
        String transactionId = String.valueOf(transakcja.getId());
        response.put("status", "success");
        response.put("message", "Płatność została zaakceptowana");
        response.put("transactionId", transactionId);

        return response;
    }
}