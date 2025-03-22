package org.example.bank.controller;

import org.example.bank.dto.PaymentRequest;
import org.example.bank.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/platnosc/blik")
public class BlikController {
    private final PaymentService paymentService;

    public BlikController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

     @PostMapping
     public ResponseEntity<Map<String, Object>> processBlikPayment(@RequestBody PaymentRequest paymentRequest) {
         Map<String, Object> result = paymentService.generateBlikCode(paymentRequest);
            if (result.get("status").equals("failed")) {
                return ResponseEntity.badRequest().body(result);
            }

         return ResponseEntity.ok(result);
     }
}
