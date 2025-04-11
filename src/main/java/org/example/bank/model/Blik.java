package org.example.bank.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Blik")
public class Blik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kontoId;

    @Column
    private int kodBlik;

    @Column
    private LocalDateTime dataWygasniecia;


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

    public LocalDateTime getDataWygasniecia() {
        return dataWygasniecia;
    }

    public void setDataWygasniecia(LocalDateTime dataWygasniecia) {
        this.dataWygasniecia = dataWygasniecia;
    }

}
