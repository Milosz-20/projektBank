package org.example.bank.dto;

public class LoginRequest {
    private String email;
    private String haslo;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getHaslo() { return haslo; }
    public void setHaslo(String haslo) { this.haslo = haslo; }
}