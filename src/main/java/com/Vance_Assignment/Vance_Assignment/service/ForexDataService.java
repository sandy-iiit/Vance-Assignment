package com.Vance_Assignment.Vance_Assignment.service;


import com.Vance_Assignment.Vance_Assignment.model.ForexData;
import com.Vance_Assignment.Vance_Assignment.repository.ForexDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ForexDataService {

    @Autowired
    private ForexDataRepository repository;

    public List<ForexData> getHistoricalData(String from, String to, String period) {
        String currencyPair = from + to + "=X";
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = switch (period) {
            case "1M" -> endDate.minusMonths(1);
            case "2M" -> endDate.minusMonths(2);
            case "3M" -> endDate.minusMonths(3);
            case "4M" -> endDate.minusMonths(4);
            case "5M" -> endDate.minusMonths(5);
            case "6M" -> endDate.minusMonths(6);
            case "7M" -> endDate.minusMonths(7);
            case "8M" -> endDate.minusMonths(8);
            case "9M" -> endDate.minusMonths(9);
            case "10M" -> endDate.minusMonths(10);
            case "11M" -> endDate.minusMonths(11);
            case "1Y" -> endDate.minusYears(1);
            default -> endDate.minusWeeks(1);
        };

        return repository.findByCurrencyPairAndDateBetween(currencyPair, startDate, endDate);
    }
}
