package org.example.bank.service;

import org.example.bank.dto.BlikPaymentRequest;
import org.example.bank.dto.PaymentRequest;
import org.example.bank.model.Blik;
import org.example.bank.model.Karta;
import org.example.bank.model.Konto;
import org.example.bank.model.Transakcja;
import org.example.bank.repository.BlikRepository;
import org.example.bank.repository.KartaRepository;
import org.example.bank.repository.KontoRepository;
import org.example.bank.repository.TransakcjaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;

@Service
public class PaymentService {
    private final KartaRepository kartaRepository;
    private final KontoRepository kontoRepository;
    private final TransakcjaRepository transakcjaRepository;
    private final BlikRepository blikRepository;

    public PaymentService(KartaRepository kartaRepository, KontoRepository kontoRepository, TransakcjaRepository transakcjaRepository, BlikRepository blikRepository) {
        this.kartaRepository = kartaRepository;
        this.kontoRepository = kontoRepository;
        this.transakcjaRepository = transakcjaRepository;
        this.blikRepository = blikRepository;
    }

    @Transactional
    public Map<String, Object> processPayment(PaymentRequest paymentRequest) {
        Map<String, Object> response = new HashMap<>();

        Optional<Karta> cardOptional = kartaRepository.findByNumerKarty(paymentRequest.getCardNumber());

        if (cardOptional.isEmpty()) {
            response.put("status", "failed");
            response.put("message", "Card does not exist");
            return response;
        }

        Karta karta = cardOptional.get();

        LocalDate cardExpiryDate = karta.getTerminWaznosci();

        YearMonth providedExpiryDate;

        try {
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
            response.put("message", "Invalid expiry date format");
            return response;
        }

        YearMonth cardExpiryYearMonth = YearMonth.from(cardExpiryDate);
        YearMonth currentMonth = YearMonth.now();

        if (!cardExpiryYearMonth.equals(providedExpiryDate) || cardExpiryYearMonth.isBefore(currentMonth)) {
            response.put("status", "failed");
            response.put("message", "Card expired or invalid expiry date");
            return response;
        }
        if (paymentRequest.getCvv().length() != 3 ) {
            response.put("status", "failed");
            response.put("message", "CVV must be 3 digits");
        } else if (!Objects.equals(karta.getCvv(), paymentRequest.getCvv())) {
            response.put("status", "failed");
            response.put("message", "Invalid CVV code");
            return response;
        }

        Integer kontoId = karta.getKontoId();
        Optional<Konto> kontoOptional = kontoRepository.findById(kontoId);

        if (kontoOptional.isEmpty()) {
            response.put("status", "failed");
            response.put("message", "Associated account not found");
            return response;
        }

        if (paymentRequest.getAmount().compareTo(karta.getLimitKarty()) > 0) {
            response.put("status", "failed");
            response.put("message", "Card limit exceeded");
            return response;
        }

        Konto konto = kontoOptional.get();
        BigDecimal amount = paymentRequest.getAmount();

        if (konto.getSaldo().compareTo(amount) < 0) {
            response.put("status", "failed");
            response.put("message", "Insufficient funds in the account");
            return response;
        }

        konto.setSaldo(konto.getSaldo().subtract(amount));
        kontoRepository.save(konto);

        Transakcja transakcja = new Transakcja();
        transakcja.setKartaId(karta.getId());
        transakcja.setKwota(amount);
        transakcja.setData(LocalDateTime.now());
        transakcja.setTypTransakcji("ZAKUP");
        transakcjaRepository.save(transakcja);

        String transactionId = String.valueOf(transakcja.getId());
        response.put("status", "success");
        response.put("message", "Payment has been accepted");
        response.put("transactionId", transactionId);

        return response;
    }

    @Transactional
    public Map<String, Object> processPaymentBlik(BlikPaymentRequest paymentRequest) {
        Map<String, Object> response = new HashMap<>();

        Integer blikCode = Integer.valueOf(paymentRequest.getBlikCode());

        Optional<Blik> blikOptional = blikRepository.findByKodBlik(blikCode);

        if (blikOptional.isEmpty()) {
            response.put("status", "failed");
            response.put("message", "Invalid BLIK code");
            return response;
        }

        Blik blik = blikOptional.get();
        LocalDateTime dataWygasniecia = blik.getDataWygasniecia();

        if (dataWygasniecia.isBefore(LocalDateTime.now())) {
            response.put("status", "failed");
            response.put("message", "BLIK code has expired");
            return response;
        }

        Integer kontoId = blik.getKartaId();
        Optional<Konto> kontoOptional = kontoRepository.findById(kontoId);

        System.out.println("KONTO ID: " + kontoId);
        System.out.println("KONTO: " + kontoOptional);

        if (kontoOptional.isEmpty()) {
            response.put("status", "failed");
            response.put("message", "Account associated with BLIK code not found");
            return response;
        }

        Konto konto = kontoOptional.get();
        BigDecimal amount = paymentRequest.getAmount();


        if (konto.getSaldo().compareTo(amount) < 0) {
            System.out.println("AMOUNT:"+amount + " " + konto.getSaldo());
            response.put("status", "failed");
            response.put("message", "Insufficient funds in the account");
            return response;
        }

        konto.setSaldo(konto.getSaldo().subtract(amount));
        kontoRepository.save(konto);

        Transakcja transakcja = new Transakcja();
        transakcja.setKartaId(konto.getId());
        transakcja.setKwota(amount);
        transakcja.setData(LocalDateTime.now());
        transakcja.setTypTransakcji("BLIK");
        transakcjaRepository.save(transakcja);

        String transactionId = String.valueOf(transakcja.getId());
        response.put("status", "success");
        response.put("message", "BLIK payment has been accepted");
        response.put("transactionId", transactionId);

        return response;
    }

    @Transactional
        public Map<String, Object> generateBlikCode(Integer kontoId) {
            Map<String, Object> response = new HashMap<>();

            int blikCode = 100000 + (int)(Math.random() * 900000);
            long validityInMillis = 2 * 60 * 1000;

            LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(2);


        Blik blik = new Blik();
        blik.setKartaId(kontoId);
        blik.setKodBlik(blikCode);
        blik.setDataWygasniecia(expiryTime);
        blikRepository.save(blik);

            response.put("status", "success");
            response.put("message", "BLIK code generated");
            response.put("blikCode", String.valueOf(blikCode));
            response.put("expiryTime", expiryTime);
            response.put("validityInMillis", validityInMillis);

            System.out.println("Wygenerowano kod BLIK: " + blikCode + " ważny do: " + expiryTime);

            return response;
        }
    }
