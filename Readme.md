LexAI - AI-Powered Legal Assistant & Lawyer Connect Platform


Helping common people understand legal procedures using AI, and connecting them with verified lawyers.



Table of Contents


About
Tech Stack
Project Structure
Database Schema
API Endpoints
Security
Getting Started
Author



About

LexAI is a production-ready REST API backend that analyzes legal problems against Indian law using AI (OpenAI GPT-4o) and returns:


Relevant IPC sections
What supports your case
What works against you
Recommended legal steps


Users can also browse verified lawyers by specialization and book consultations directly through the platform.


Tech Stack

LayerTechnologyLanguageJava 21FrameworkSpring Boot 3.5SecuritySpring Security, JWT, BCryptDatabasePostgreSQL (Neon.tech)AIOpenAI GPT-4o APIDevOpsDocker, RailwayDocumentationSwagger / OpenAPIToolsMaven, Postman, IntelliJ IDEA


Project Structure

src/main/java/com/lexai/lexaibackend/
|
+-- config/
|   +-- SecurityConfig.java            # JWT filter, security rules
|
+-- controller/
|   +-- AuthController.java            # Register, Login
|   +-- LegalQueryController.java      # Submit and fetch queries
|   +-- UserController.java            # User management
|
+-- service/
|   +-- AuthService.java               # Registration and login logic
|   +-- JwtService.java                # Token generation and validation
|   +-- LegalQueryService.java         # Legal query business logic
|   +-- UserService.java               # User business logic
|
+-- repository/
|   +-- LegalQueryRepository.java      # Legal query database operations
|   +-- LawyerProfileRepository.java   # Lawyer profile database operations
|   +-- UserRepository.java            # User database operations
|
+-- model/
|   +-- LegalQuery.java                # Legal query entity
|   +-- LawyerProfile.java             # Lawyer profile entity
|   +-- User.java                      # User entity


Database Schema

+------------------+          +-------------------------+
|      users       |          |      legal_queries      |
+------------------+          +-------------------------+
| id (PK)          |<---------| id (PK)                 |
| name             |          | user_id (FK)            |
| email (unique)   |          | problem_text            |
| password (hashed)|          | category                |
| role             |          | created_at              |
| created_at       |          +-------------------------+
+------------------+
|
|
v
+----------------------+
|    lawyer_profiles   |
+----------------------+
| id (PK)              |
| user_id (FK)         |
| specialization       |
| experience           |
| rating               |
| available            |
+----------------------+

Entity Relationships


One User can have many LegalQueries (OneToMany)
One User can have one LawyerProfile (OneToOne)
One LegalQuery belongs to one User (ManyToOne)



API Endpoints

Auth - No token required

MethodEndpointDescriptionPOST/api/auth/registerRegister a new userPOST/api/auth/loginLogin and receive JWT token

Legal Queries - Token required

MethodEndpointDescriptionPOST/api/legal/submitSubmit a legal problemGET/api/legal/allFetch all legal queries

Users - Token required

MethodEndpointDescriptionGET/api/users/{id}Get user by ID


Security

All endpoints except /api/auth/** require a valid JWT token.

Include the token in every protected request header:

Authorization: Bearer your_jwt_token_here

FeatureImplementationPassword StorageBCrypt hashing - plain text never storedAuthenticationJWT (JSON Web Tokens)Token Expiry1 hourAuthorizationRole-based access control (USER / LAWYER / ADMIN)StatelessNo server-side sessions


Getting Started

Prerequisites


Java 21+
Maven
PostgreSQL database (or free Neon.tech account)


Installation

1. Clone the repository

bashgit clone https://github.com/Bhavya-jain07/lexai-backend.git
cd lexai-backend

2. Configure application.properties

propertiesspring.datasource.url=your_database_url
spring.datasource.username=your_username
spring.datasource.password=your_password
jwt.secret=your_jwt_secret
jwt.expiration=3600000

3. Run the application

bashmvn spring-boot:run

4. API is available at

http://localhost:8080

Testing the API

Register a user:

bashcurl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"name":"Rahul","email":"rahul@gmail.com","password":"pass123","role":"USER"}'

Login and get token:

bashcurl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"email":"rahul@gmail.com","password":"pass123"}'

Submit a legal query (use token from login response):

bashcurl -X POST http://localhost:8080/api/legal/submit \
-H "Content-Type: application/json" \
-H "Authorization: Bearer your_token_here" \
-d '{"problemText":"My landlord is not returning my deposit","category":"CIVIL"}'


Author

Kavya Agarwal
B.Tech CSE - GLA University, Mathura