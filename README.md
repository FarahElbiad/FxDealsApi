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

---
### 


