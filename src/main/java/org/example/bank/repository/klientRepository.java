package org.example.bank.repository;

import org.example.bank.model.Klient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface klientRepository extends JpaRepository<Klient, Integer> {
}