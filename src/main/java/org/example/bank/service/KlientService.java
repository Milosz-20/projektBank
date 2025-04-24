package org.example.bank.service;

import org.example.bank.dto.LoginRequest;
import org.example.bank.model.Klient;
import org.example.bank.repository.klientRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class KlientService {
    private final klientRepository klientRepository;

    public KlientService(klientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }

    public List<Klient> getAllKlienci() {
        return klientRepository.findAll();
    }

    public Map<String, Object> login(LoginRequest loginRequest) {
        Map<String, Object> response = new HashMap<>();

        System.out.println("Próba logowania klienta: " + loginRequest.getEmail());

        Optional<Klient> klientOpt = klientRepository.findByEmail(loginRequest.getEmail());
        if (klientOpt.isPresent()) {
            Klient klient = klientOpt.get();
            // for now without hashing
            if (loginRequest.getHaslo().equals(klient.getHaslo())) {
                response.put("status", "success");
                response.put("message", "Login successful");
                response.put("klientId", klient.getId());
                System.out.println("Zalogowano klienta: " + klient.getImie() + " " + klient.getNazwisko());
            } else {
                response.put("status", "error");
                response.put("message", "Invalid password");
                System.out.println("Nieprawidłowe hasło dla klienta: " + klient.getImie() + " " + klient.getNazwisko());
            }
        } else {
            response.put("status", "error");
            response.put("message", "User with the provided email does not exist");
            System.out.println("Nie znaleziono klienta o adresie email: " + loginRequest.getEmail());
        }

        return response;
    }
}
