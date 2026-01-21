# 🚀 Job Portal Backend (Spring Boot + PostgreSQL)

This is the **backend service** for the Job Portal application, built using **Spring Boot**, **PostgreSQL**, and **Docker**, and deployed on **Render**.

It provides REST APIs to manage job postings including create, update, delete, search, and list operations.

---
## 🌐 Live Website

https://697081526e097906f144be90--dazzling-sable-e12539.netlify.app/


## 🌐 Live API

**Base URL**

https://job-portal-backend-o6do.onrender.com/jobs


**Test Endpoint**

GET /jobs


---

## 🛠 Tech Stack

- Java 21  
- Spring Boot 4  
- Spring Data JPA  
- Hibernate  
- PostgreSQL  
- Docker  
- Render (Cloud Hosting)

---

## 📦 Features

- Create new job post  
- View all job posts  
- Search jobs by keyword  
- Update job post  
- Delete job post  
- PostgreSQL database integration  
- Deployed with Docker on Render  

---


---

## 🔗 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /jobs | Get all jobs |
| GET | /jobs/keyword/{key} | Search jobs |
| POST | /job | Add new job |
| PUT | /job | Update job |
| DELETE | /job/{id} | Delete job |

---

## ⚙️ Environment Variables (Render)

Set these in the Render dashboard:

| Variable | Description |
|----------|-------------|
| DB_URL | JDBC URL for PostgreSQL |
| DB_USER | Database username |
| DB_PASS | Database password |

**Example**
DB_URL=jdbc:postgresql://host:5432/dbname?sslmode=require
DB_USER=your_db_user
DB_PASS=your_db_password


---

## ▶️ Run Locally

### 1. Clone

git clone https://github.com/pranitlavangare0007/job-portal.git
cd job-portal

2 Configure Database

Update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/demo
spring.datasource.username=postgres
spring.datasource.password=yourpassword

3. Run
mvn spring-boot:run

Server runs at:

http://localhost:8080



