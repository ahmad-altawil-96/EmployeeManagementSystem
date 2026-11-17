# Employee Management System

A REST API for managing employees, departments and locations, built with Spring Boot.
It uses JWT authentication and role-based access control.

## Tech Stack

- Java 21, Spring Boot
- Spring Data JPA (Hibernate), PostgreSQL
- Spring Security with JWT
- Bean Validation
- Swagger / OpenAPI (springdoc)
- JUnit 5 and Mockito
- Lombok, Gradle

## Features

- Login with email and password; the API returns a JWT token
- Role-based access (`ADMIN`, `EMPLOYEE`)
- Create, read, update and deactivate employees
- Input validation on request DTOs
- Global exception handling with clear error responses
- Default admin account created on first start

## Project Structure

```
src/main/java/com/example/EmployeeManagementSystem
├── config       # Swagger config, initial data
├── controller   # REST endpoints
├── dto          # Request and response objects
├── exception    # Custom exceptions and global handler
├── model        # JPA entities and enums
├── repository   # Spring Data repositories
├── security     # JWT filter, JWT service, security config
└── Service      # Business logic
```

## Getting Started

### Requirements

- Java 21
- PostgreSQL

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/ahmad-altawil-96/EmployeeManagementSystem.git
   cd EmployeeManagementSystem
   ```
2. Create a PostgreSQL database named `employee_management`.
3. Copy `src/main/resources/application.example.properties` to `application.properties` in the same folder and fill in:
   - your database username and password
   - `jwt.secret` (at least 32 characters)
4. Start the application:
   ```bash
   ./gradlew bootRun
   ```

The API runs on `http://localhost:8080`.

### Default Admin (local development)

| Email               | Password   |
|---------------------|------------|
| admin@company.com   | admin123   |

## API Documentation

Swagger UI: `http://localhost:8080/swagger-ui.html`

### Endpoints

| Method | Endpoint                | Description             | Access          |
|--------|-------------------------|-------------------------|-----------------|
| POST   | `/auth/login`           | Log in and get a JWT    | Public          |
| POST   | `/employee`             | Create an employee      | ADMIN, EMPLOYEE |
| GET    | `/employee`             | Get all employees       | ADMIN, EMPLOYEE |
| GET    | `/employee/{id}`        | Get one employee        | ADMIN, EMPLOYEE |
| PUT    | `/employee/{id}`        | Update an employee      | ADMIN, EMPLOYEE |
| PATCH  | `/employee/{id}/status` | Deactivate an employee  | ADMIN, EMPLOYEE |

Send the token in the header of protected requests:

```
Authorization: Bearer <token>
```

## Running Tests

```bash
./gradlew test
```

## Planned

- Attendance and break tracking
- Admin-only endpoints for managing employees
