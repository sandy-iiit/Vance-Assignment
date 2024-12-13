package com.Vance_Assignment.Vance_Assignment.service;

import com.Vance_Assignment.Vance_Assignment.model.ForexData;
import com.Vance_Assignment.Vance_Assignment.repository.ForexDataRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class ScraperService {

    @Autowired
    private ForexDataRepository repository;

    public void scrapeData(String currencyPair, LocalDate startDate, LocalDate endDate) {
        long period1 = startDate.toEpochDay() * 86400;
        long period2 = endDate.toEpochDay() * 86400;

        String url = String.format(
                "https://finance.yahoo.com/quote/%s/history/?period1=%d&period2=%d",
                currencyPair, period1, period2
        );

        try {
            Document doc = Jsoup.connect(url).get();
            Elements rows = doc.select("table.table tbody tr");
            System.out.println("Rows size: " + rows.size());

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

            for (var row : rows) {
                Elements cols = row.select("td");
                String dateStr = cols.get(0).text().trim();
                String open = cols.get(1).text().replace(",", "");
                String high = cols.get(2).text().replace(",", "");
                String low = cols.get(3).text().replace(",", "");
                String close = cols.get(4).text().replace(",", "");

                ForexData forexData = new ForexData();
                forexData.setCurrencyPair(currencyPair);

                // Try parsing the date
                try {
                    forexData.setDate(LocalDate.parse(dateStr, dateFormatter));
                } catch (Exception e) {
                    System.out.println("Failed to parse date: " + dateStr);
                    System.out.println(e);
                    continue;
                }

                forexData.setOpen(Double.parseDouble(open));
                forexData.setHigh(Double.parseDouble(high));
                forexData.setLow(Double.parseDouble(low));
                forexData.setClose(Double.parseDouble(close));

                repository.save(forexData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

