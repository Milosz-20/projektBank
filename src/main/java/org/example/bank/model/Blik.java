package org.example.bank.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Blik")
public class Blik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kontoId;

    @Column
    private int kodBlik;

    @Column
    private LocalDate dataWygasniecia;


    public Integer getKontoId() {
        return kontoId;
    }

    public void setKontoId(Integer kontoId) {
        this.kontoId = kontoId;
    }

    public int getKodBlik() {
        return kodBlik;
    }

    public void setKodBlik(int kodBlik) {
        this.kodBlik = kodBlik;
    }

    public LocalDate getDataWygasniecia() {
        return dataWygasniecia;
    }

    public void setDataWygasniecia(LocalDate dataWygasniecia) {
        this.dataWygasniecia = dataWygasniecia;
    }

}
