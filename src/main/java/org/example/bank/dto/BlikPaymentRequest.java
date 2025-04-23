package org.example.bank.dto;

import java.math.BigDecimal;

public class BlikPaymentRequest {
    private String blikCode;
    private BigDecimal amount;
    private Integer idKonta;


    public BlikPaymentRequest() {}

    public BlikPaymentRequest(String blikCode, BigDecimal amount, Integer idKonta) {
        this.blikCode = blikCode;
        this.amount = amount;
        this.idKonta = idKonta;
    }

    public Integer getIdKonta() {
        return idKonta;
    }

    public void setIdKonta(Integer idKonta) {
        this.idKonta = idKonta;
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