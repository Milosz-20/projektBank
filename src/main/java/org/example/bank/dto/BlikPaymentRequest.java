package org.example.bank.dto;

import java.math.BigDecimal;

public class BlikPaymentRequest {
    private String blikCode;
    private BigDecimal amount;



    public BlikPaymentRequest() {}

    public BlikPaymentRequest(String blikCode, BigDecimal amount, Integer idKonta) {
        this.blikCode = blikCode;
        this.amount = amount;
    }

    public String getBlikCode() {
        return blikCode;
    }

    public void setBlikCode(String blikCode) {
        this.blikCode = blikCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}