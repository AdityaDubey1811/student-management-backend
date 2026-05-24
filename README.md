# Student Management Backend

## Features
- CRUD Operations for Students
- JWT Authentication & Authorization
- Role-Based Access Control (ADMIN / STUDENT)
- DTO Validation using Jakarta Validation
- Global Exception Handling
- RESTful APIs
- Environment Profiles (dev/prod)
- Unit & Controller Testing
- CI Pipeline using GitHub Actions

## Tech Stack
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT
- Maven
- JUnit 
- GitHub Actions

## API Endpoints

### Student APIs
- POST /students
- GET /students
- GET /students/{id}
- PUT /students/{id}
- DELETE /students/{id}

### Auth APIs
- POST /api/users
- POST /api/users/login

## Authentication
JWT-based authentication is implemented using Spring Security.

## CI/CD
GitHub Actions workflow added for automated Maven build.
