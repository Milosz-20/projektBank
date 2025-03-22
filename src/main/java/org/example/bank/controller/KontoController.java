package org.example.bank.controller;


import org.example.bank.service.KontoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/konto")

public class KontoController {
    private final KontoService kontoService;

    public KontoController(KontoService kontoService) {
        this.kontoService = kontoService;
    }

    @GetMapping("/{kontoId}/saldo")
    public List<BigDecimal> getSaldo(@PathVariable Long kontoId) {
        return kontoService.getSaldo(Math.toIntExact(kontoId));
    }

}