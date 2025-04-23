package org.example.bank.repository;

import org.example.bank.model.Karta;
import org.example.bank.model.Klient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KartaRepository extends JpaRepository<Karta, Integer> {
    Optional<Karta> findByNumerKarty(String numerKarty);
    List<Karta> findByKontoId(Integer kontoId);
}