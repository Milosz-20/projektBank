package org.example.bank.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transakcje")
public class Transakcja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "karta_id")
    private Integer kartaId;

    private LocalDateTime data;
    private BigDecimal kwota;

    @Column(name = "typ_transakcji")
    private String typTransakcji;

    @ManyToOne
    @JoinColumn(name = "karta_id", insertable = false, updatable = false)
    private Karta karta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKartaId() {
        return kartaId;
    }

    public void setKartaId(Integer kartaId) {
        this.kartaId = kartaId;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }

    public String getTypTransakcji() {
        return typTransakcji;
    }

    public void setTypTransakcji(String typTransakcji) {
        this.typTransakcji = typTransakcji;
    }

    public Karta getKarta() {
        return karta;
    }

    public void setKarta(Karta karta) {
        this.karta = karta;
    }
}