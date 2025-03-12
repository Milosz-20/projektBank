package org.example.bank.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "karty")
public class Karta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "konto_id")
    private Integer kontoId;

    @Column(name = "numer_karty")
    private String numerKarty;

    @Column(name = "termin_waznosci")
    private LocalDate terminWaznosci;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "limit_karty")
    private BigDecimal limitKarty;

    @ManyToOne
    @JoinColumn(name = "konto_id", insertable = false, updatable = false)
    private Konto konto;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKontoId() {
        return kontoId;
    }

    public void setKontoId(Integer kontoId) {
        this.kontoId = kontoId;
    }

    public String getNumerKarty() {
        return numerKarty;
    }

    public void setNumerKarty(String numerKarty) {
        this.numerKarty = numerKarty;
    }

    public LocalDate getTerminWaznosci() {
        return terminWaznosci;
    }

    public void setTerminWaznosci(LocalDate terminWaznosci) {
        this.terminWaznosci = terminWaznosci;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public BigDecimal getLimitKarty() {
        return limitKarty;
    }

    public void setLimitKarty(BigDecimal limitKarty) {
        this.limitKarty = limitKarty;
    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }
}