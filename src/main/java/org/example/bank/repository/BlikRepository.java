package org.example.bank.repository;

import org.example.bank.model.Blik;
import org.example.bank.model.Karta;
import org.example.bank.model.Transakcja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlikRepository extends JpaRepository<Blik, Integer> {
    Optional<Blik> findByKodBlik(Integer kodBlik);
}
