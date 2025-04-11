package org.example.bank.repository;

import org.example.bank.model.Blik;
import org.example.bank.model.Transakcja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlikRepository extends JpaRepository<Blik, Integer> {

}
