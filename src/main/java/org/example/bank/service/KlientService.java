package org.example.bank.service;

import org.example.bank.model.Klient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlientService {
    private final org.example.bank.repository.klientRepository klientRepository;

    public KlientService(org.example.bank.repository.klientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }

    public List<Klient> getAllKlienci() {
        return klientRepository.findAll();
    }
}