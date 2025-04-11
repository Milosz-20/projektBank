package org.example.bank.controller;

import org.example.bank.dto.PaymentRequest;
import org.example.bank.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/platnosc/blik")
public class BlikController {
    private final PaymentService paymentService;

    public BlikController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }



     @GetMapping("/{kontoId}")
        public ResponseEntity<Map<String, Object>> processBlikPayment(@PathVariable String kontoId) {
            Map<String, Object> result = paymentService.generateBlikCode(Integer.valueOf(kontoId));
            if (result.get("status").equals("failed")) {
                return ResponseEntity.badRequest().body(result);
            }
            return ResponseEntity.ok(result);
        }
}
