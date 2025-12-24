# FX Deals Import Service

## 📌 Overview

This project is a Spring Boot REST API developed to import and persist FX deals into a database.  
It was implemented as part of a technical assignment to demonstrate backend development skills, data validation, deduplication, and partial data processing without rollback.

The system accepts FX deal data, validates each record, prevents duplicate imports, and stores valid deals while reporting errors for invalid ones.

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker & Docker Compose
- Maven
- SLF4J Logging
- Postman (API Testing)

---

## 📐 Architecture

- **Controller Layer** – Handles HTTP requests
- **Service Layer** – Business logic (validation, deduplication, persistence)
- **Repository Layer** – Database access using JPA
- **Model Layer** – Entity and DTO classes

---

## 📥 API Endpoint

### Import FX Deals

**POST** `/api/deals/import`

**Content-Type:** `application/json`

### Request Body Example

```json
[
  {
    "dealUniqueId": "D001",
    "fromCurrency": "USD",
    "toCurrency": "EUR",
    "dealTimestamp": "2024-01-01T10:00:00Z",
    "amount": 1000
  },
  {
    "dealUniqueId": "D346",
    "fromCurrency": "EUR",
    "toCurrency": "USD",
    "dealTimestamp": "2024-01-01T10:00:00Z",
    "amount": 1000
  }
]

✅ Business Rules Implemented

Validates required fields

Prevents duplicate deal imports using unique deal ID

No rollback: valid deals are saved even if some fail

Partial success supported

Detailed error reporting per request

Logging for processing and error tracking

🗄 Database

PostgreSQL

Deals are persisted using Spring Data JPA

Unique constraint on deal_unique_id

🐳 Running with Docker
Start PostgreSQL
docker compose up -d

Docker Compose Configuration
services:
  postgres:
    image: postgres:15
    container_name: fx-postgres
    environment:
      POSTGRES_DB: fx_deals
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"

▶ Running the Application
./mvnw spring-boot:run


Application runs on:

http://localhost:9090

🧪 API Testing

Tested using Postman



Validated error handling and partial success behavior

📝 Logging

Implemented using SLF4J

Logs processing steps and validation errors

Helps trace failures without stopping the import process
