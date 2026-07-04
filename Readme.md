# LexAI - AI-Powered Legal Assistant & Lawyer Connect Platform

A production-ready REST API backend that helps common people understand legal procedures by analyzing their problems against Indian law using AI, and connecting them with verified lawyers.

[![GitHub](https://img.shields.io/badge/GitHub-nova1128-black?logo=github)](https://github.com/nova1128)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Kavya%20Agarwal-blue?logo=linkedin)](https://www.linkedin.com/in/kavya-agarwal-787007327/)
[![LeetCode](https://img.shields.io/badge/LeetCode-nova2811-orange?logo=leetcode)](https://leetcode.com/u/nova2811/)

---

## Table of Contents

- [About](#about)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [Security](#security)
- [Getting Started](#getting-started)
- [Author](#author)

---

## About

LexAI is a backend REST API that solves a real problem — most common people in India have no idea about their legal rights or what procedures apply to their situation. LexAI lets a user describe their legal problem in plain language, and the system analyzes it against Indian law using AI (OpenAI GPT-4o), returning relevant IPC sections, what supports their case, what works against them, and recommended next steps.

The platform also has a Lawyer Connect module where users can browse verified lawyers by specialization and book consultations — so the AI assists rather than replaces legal professionals.

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java 21 |
| Framework | Spring Boot 3.5 |
| Security | Spring Security, JWT, BCrypt |
| Database | PostgreSQL hosted on Neon.tech |
| AI Integration | OpenAI GPT-4o API |
| DevOps | Docker, Railway |
| Documentation | Swagger / OpenAPI |
| Build Tool | Maven |

---

## Project Structure

```
src/main/java/com/lexai/lexaibackend/
├── config/
│   └── SecurityConfig.java            # JWT filter and security rules
├── controller/
│   ├── AuthController.java            # Register and login endpoints
│   ├── LegalQueryController.java      # Submit and fetch legal queries
│   └── UserController.java            # User management
├── service/
│   ├── AuthService.java               # Registration and login logic
│   ├── JwtService.java                # Token generation and validation
│   ├── LegalQueryService.java         # Legal query business logic
│   └── UserService.java               # User business logic
├── repository/
│   ├── LegalQueryRepository.java
│   ├── LawyerProfileRepository.java
│   └── UserRepository.java
└── model/
    ├── LegalQuery.java
    ├── LawyerProfile.java
    └── User.java
```

---

## Database Schema

**users**

| Column | Type | Notes |
|--------|------|-------|
| id | BIGINT | Primary key, auto-increment |
| name | VARCHAR | |
| email | VARCHAR | Unique, not null |
| password | VARCHAR | BCrypt hashed, not null |
| role | VARCHAR | USER / LAWYER / ADMIN |
| created_at | TIMESTAMP | Auto-set on insert |

**legal_queries**

| Column | Type | Notes |
|--------|------|-------|
| id | BIGINT | Primary key, auto-increment |
| user_id | BIGINT | Foreign key to users |
| problem_text | TEXT | |
| category | VARCHAR | CIVIL / CRIMINAL / PROPERTY / FAMILY |
| created_at | TIMESTAMP | Auto-set on insert |

**lawyer_profiles**

| Column | Type | Notes |
|--------|------|-------|
| id | BIGINT | Primary key, auto-increment |
| user_id | BIGINT | Foreign key to users (unique) |
| specialization | VARCHAR | |
| experience | INTEGER | Years of experience |
| rating | DOUBLE | |
| available | BOOLEAN | |

### Relationships

- One User has many LegalQueries (OneToMany)
- One User has one LawyerProfile (OneToOne)
- One LegalQuery belongs to one User (ManyToOne)

---

## API Endpoints

### Auth — No token required

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and receive JWT token |

### Legal Queries — Token required

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/legal/submit` | Submit a legal problem |
| GET | `/api/legal/all` | Fetch all legal queries |

### Users — Token required

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users/{id}` | Get user by ID |

---

## Security

All endpoints except `/api/auth/**` require a valid JWT token passed in the Authorization header.

```
Authorization: Bearer your_jwt_token_here
```

| Feature | Implementation |
|---------|----------------|
| Password storage | BCrypt hashing — plain text never stored |
| Authentication | JWT (JSON Web Tokens) |
| Token expiry | 1 hour |
| Authorization | Role-based access control (USER / LAWYER / ADMIN) |
| Session policy | Stateless — no server-side sessions |

---

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven
- PostgreSQL database (or free [Neon.tech](https://neon.tech) account)

### Installation

**1. Clone the repository**

```bash
git clone https://github.com/nova1128/lexai-backend.git
cd lexai-backend
```

**2. Set up your database on [Neon.tech](https://neon.tech) and configure `application.properties`**

```properties
spring.datasource.url=your_neon_database_url
spring.datasource.username=your_username
spring.datasource.password=your_password
jwt.secret=your_256_bit_hex_secret
jwt.expiration=3600000
```

**3. Run the application**

```bash
mvn spring-boot:run
```

**4. API is live at `http://localhost:8080`**

### Quick API Test

Register:
```bash
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"name":"Rahul","email":"rahul@gmail.com","password":"pass123","role":"USER"}'
```

Login:
```bash
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"email":"rahul@gmail.com","password":"pass123"}'
```

Submit a legal query (use token from login):
```bash
curl -X POST http://localhost:8080/api/legal/submit \
-H "Content-Type: application/json" \
-H "Authorization: Bearer your_token_here" \
-d '{"problemText":"My landlord is not returning my security deposit","category":"CIVIL"}'
```

---

## Author

**Kavya Agarwal**

B.Tech CSE — GLA University, Mathura (2024-2028)

[![GitHub](https://img.shields.io/badge/GitHub-nova1128-black?logo=github)](https://github.com/nova1128)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Kavya%20Agarwal-blue?logo=linkedin)](https://www.linkedin.com/in/kavya-agarwal-787007327/)
[![LeetCode](https://img.shields.io/badge/LeetCode-nova2811-orange?logo=leetcode)](https://leetcode.com/u/nova2811/)