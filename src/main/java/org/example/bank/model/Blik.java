package org.example.bank.model;

import jakarta.persistence.*; // Upewnij się, że importy są poprawne dla Jakarta EE
import java.time.LocalDateTime;

@Entity
@Table(name = "Blik")
public class Blik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer kartaId;

    @Column(nullable = false)
    private int kodBlik;

    @Column(nullable = false)
    private LocalDateTime dataWygasniecia;

    // Gettery i settery dla nowego pola id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKartaId() {
        return kartaId;
    }

    public void setKartaId(Integer kontoId) {
        this.kartaId = kontoId;
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