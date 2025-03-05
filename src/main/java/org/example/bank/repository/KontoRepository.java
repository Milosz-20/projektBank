package org.example.bank.repository;

import org.example.bank.model.Konto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KontoRepository extends JpaRepository<Konto, Integer> {
}