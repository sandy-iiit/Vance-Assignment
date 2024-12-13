# Forex Data Scraper and API

## Project Overview

This Spring Boot application is a Forex Data Scraper and API that retrieves historical currency exchange rate data from Yahoo Finance. The application automatically scrapes and stores forex data for multiple currency pairs, providing an easy-to-use API for accessing historical exchange rates.

## Features

- Automatic daily data scraping for multiple currency pairs
- RESTful API to retrieve historical forex data
- Swagger UI for API documentation
- Supports various time period queries
- Scrapes data for GBPINR, AEDINR, USDINR, and EURINR currency pairs

## Prerequisites

- Java 17 or higher
- Maven
- Internet connection (for data scraping)

## Installation

### Clone the Repository

```bash
git clone https://github.com/sandy-iiit/Vance-Assignment.git
cd Vance-Assignment
```

### Build the Project

```bash
mvn clean install
```

## Running the Application

### Using Maven

```bash
mvn spring-boot:run
```

### Using JAR File

If you use the pre-built JAR file available in the above repo:

```bash
java -jar Vance_Assignment-0.0.1-SNAPSHOT.jar
```
Running the application creates an sqlite database in the name of database.db where the scraped data is stored periodically.
## API Usage

### Accessing Swagger UI

Open your browser and navigate to:
```
http://localhost:3000/swagger-ui.html
```

### Making API Requests

The application provides a POST endpoint to retrieve historical forex data:

- **Endpoint:** `http://localhost:3000/api/forex-data`
- **Method:** POST
- **Content-Type:** application/json

#### Request Body Example

```json
{
  "from": "USD",
  "to": "INR",
  "period": "6M"
}
```

#### Supported Periods

- `1M`, `2M`, `3M`, `4M`, `5M`, `6M`, `7M`, `8M`, `9M`, `10M`, `11M`
- `1Y` (1 Year)
- Default is 1 week if an unrecognized period is provided

## Data Scraping

The application automatically scrapes data:
- On application startup
- Every two hours from startup (configurable via cron expression)

Currency pairs scraped for a period of 1 year(which is configurable at the main method) :
- GBPINR
- AEDINR
- USDINR
- EURINR

## Technology Stack

- Spring Boot
- Spring Data JPA
- Jsoup (for web scraping)
- Swagger (API documentation)
- SQLite Database (automatically created as `database.db`)

## Customization

### Changing Scraping Frequency

Modify the `@Scheduled` annotation in `VanceAssignmentApplication.java`:
- Current: For every 2 hours from the start of application: `@Scheduled(cron = "0 0 */2 * * ?")` 
- Can be configured for every 2 minutes: `@Scheduled(cron = "0 0/2 * * * ?")`

## Logging and Monitoring

- Check console logs for scraping details
- Errors are printed to the console during data scraping

## Database

### SQLite Database

- The application automatically creates a `database.db` SQLite database file in the project directory when run for the first time.
- All forex data is persistently stored in this database.
- The database is recreated daily during the scheduled scraping process, ensuring fresh and up-to-date data.

