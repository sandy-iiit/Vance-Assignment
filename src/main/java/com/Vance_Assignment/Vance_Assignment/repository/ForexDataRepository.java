package com.Vance_Assignment.Vance_Assignment.repository;
import com.Vance_Assignment.Vance_Assignment.model.ForexData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ForexDataRepository extends JpaRepository<ForexData, Long> {
    List<ForexData> findByCurrencyPairAndDateBetween(String currencyPair, LocalDate date, LocalDate date2);
}

