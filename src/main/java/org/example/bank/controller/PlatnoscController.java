package org.example.bank.controller;

import org.example.bank.dto.BlikPaymentRequest;
import org.example.bank.dto.PaymentRequest;
import org.example.bank.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/platnosc")
public class PlatnoscController {
    private final PaymentService paymentService;

    public PlatnoscController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> processPayment(@RequestBody PaymentRequest paymentRequest) {
        Map<String, Object> result = paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/blik")
    public ResponseEntity<Map<String, Object>> processPaymentBlik(@RequestBody BlikPaymentRequest blikPaymentRequest) {
        Map<String, Object> result = paymentService.processPaymentBlik(blikPaymentRequest);
        return ResponseEntity.ok(result);
    }
}