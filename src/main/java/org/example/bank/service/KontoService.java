package org.example.bank.service;

import org.example.bank.model.Karta;
import org.example.bank.model.Konto;
import org.example.bank.repository.KartaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class KontoService {
    private final org.example.bank.repository.KontoRepository kontoRepository;
    private final KartaRepository kartaRepository;

    public KontoService(org.example.bank.repository.KontoRepository kontoRepository, KartaRepository kartaRepository) {
        this.kontoRepository = kontoRepository;
        this.kartaRepository = kartaRepository;
    }

    public List<BigDecimal> getSaldo(Integer kontoId) {
        Optional<Konto> konto = kontoRepository.findById(kontoId);
        System.out.println("GET SALDO" + kontoId);
        if (konto.isPresent()) {
            return getSaldaKlienta(kontoId);
        } else {
            throw new RuntimeException("Account with given ID does not exist");
        }
    }

    public List<BigDecimal> getSaldaKlienta(Integer klientId) {
        List<Konto> kontaKlienta = kontoRepository.findByKlientId(klientId);
        System.out.println("GET SALDA KLIENTA" + klientId);
        if (kontaKlienta.isEmpty()) {
            throw new RuntimeException("Client with given ID has no accounts");
        }
        return kontaKlienta.stream()
                .map(Konto::getSaldo)
                .collect(Collectors.toList());
    }

    public java.util.stream.Stream<Karta> getKarty(Long kontoId) {
        System.out.println("GET KARTY dla konta: " + kontoId);
        List<Karta> karty = kartaRepository.findByKontoId(kontoId.intValue());

        if (karty.isEmpty()) {
            throw new RuntimeException("Account with ID " + kontoId + " has no assigned cards");
        }

        return karty.stream();
    }
}