package org.example.bank.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "klienci")
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imie;
    private String nazwisko;
    private String adres;
    private String telefon;

    @Column(name = "data_urodzenia")
    private LocalDate dataUrodzenia;

    @Column(unique = true)
    private String email;
    private String haslo;

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
    public String getHaslo() {
        return haslo;
    }


    public Klient() {
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }
}