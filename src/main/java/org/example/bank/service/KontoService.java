package org.example.bank.service;

import org.example.bank.model.Konto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class KontoService {
    private final org.example.bank.repository.KontoRepository kontoRepository;

    public KontoService(org.example.bank.repository.KontoRepository kontoRepository) {
        this.kontoRepository = kontoRepository;
    }

    public List<BigDecimal> getSaldo(Integer kontoId) {
        Optional<Konto> konto = kontoRepository.findById(kontoId);
        System.out.println("GET SALDO" + kontoId);
        if (konto.isPresent()) {
            return getSaldaKlienta(kontoId);
        } else {
            throw new RuntimeException("Konto o podanym ID nie istnieje");
        }
    }

    public List<BigDecimal> getSaldaKlienta(Integer klientId) {
        List<Konto> kontaKlienta = kontoRepository.findByKlientId(klientId);
        System.out.println("GET SALDA KLIENTA" + klientId);
        if (kontaKlienta.isEmpty()) {
            throw new RuntimeException("Klient o podanym ID nie ma żadnych kont");
        }
        return kontaKlienta.stream()
                .map(Konto::getSaldo)
                .collect(Collectors.toList());
    }
}