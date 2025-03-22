package org.example.bank.controller;


import org.example.bank.model.Klient;
import org.example.bank.service.KlientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/klienci")

public class KlientController {
    private final KlientService klientService;

    public KlientController(KlientService klientService) {
        this.klientService = klientService;
    }

    @GetMapping
    public List<Klient> getAllKlienci() {
        return klientService.getAllKlienci();
    }

}