package com.Vance_Assignment.Vance_Assignment;

import com.Vance_Assignment.Vance_Assignment.repository.ForexDataRepository;
import com.Vance_Assignment.Vance_Assignment.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class VanceAssignmentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(VanceAssignmentApplication.class, args);
	}
	@Autowired
	private ScraperService scraperService;

	@Autowired
	private ForexDataRepository forexDataRepository;

	@Override
	public void run(String... args) throws Exception {
		// Scrape data when the application starts
		forexDataRepository.deleteAll();
		LocalDate endDate = LocalDate.now();
		LocalDate startDate = endDate.minusYears(1); // Scrape data for the last year

		// Scrape data for both currency pairs
		scraperService.scrapeData("GBPINR=X", startDate, endDate);
		scraperService.scrapeData("AEDINR=X", startDate, endDate);
		scraperService.scrapeData("USDINR=X", startDate, endDate);
		scraperService.scrapeData("EURINR=X", startDate, endDate);
	}

	    @Scheduled(cron = "0 0 0 * * ?") // Daily at midnight
//	@Scheduled(cron = "0 0/2 * * * ?") // Every 2 minutes
	public void scrapeDailyData() {
		forexDataRepository.deleteAll();

		LocalDate endDate = LocalDate.now();
		LocalDate startDate = endDate.minusYears(1);

		scraperService.scrapeData("GBPINR=X", startDate, endDate);
		scraperService.scrapeData("AEDINR=X", startDate, endDate);
		scraperService.scrapeData("USDINR=X", startDate, endDate);
		scraperService.scrapeData("EURINR=X", startDate, endDate);
	}


}
