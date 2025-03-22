package org.example.bank.repository;

import org.example.bank.model.Klient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface klientRepository extends JpaRepository<Klient, Integer> {
    Optional<Klient> findByEmail(String email);
}