package org.example.bank.repository;

import org.example.bank.model.Transakcja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransakcjaRepository extends JpaRepository<Transakcja, Integer> {
}