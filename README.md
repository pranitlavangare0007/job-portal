🚀 Job Portal Backend (Spring Boot + JWT + OAuth2) 

A secure, production-ready backend for a Job Portal application built with Spring Boot, featuring:

🔐 JWT authentication

🌐 Google OAuth2 login

👥 Role-based access (USER / ADMIN)

🗄️ PostgreSQL (Render Cloud DB)

🌍 CORS-ready for React frontend

☁️ Deployed on Render

🛠️ Tech Stack

Layer	Technology
Backend	Spring Boot 3 / Java 21
Security	Spring Security, JWT, OAuth2
Database	PostgreSQL (Render)
ORM	Hibernate / JPA
Build	Maven
Deployment	Render


✨ Features

User registration & login

BCrypt password hashing

JWT token generation & validation

Google OAuth2 login

Automatic JWT creation after OAuth

Role-based authorization

Admin-only routes

CORS configuration for frontend

REST APIs for jobs & users

🔐 Authentication Flow

Normal Login

POST /login → returns JWT
Authorization: Bearer <token>

Google OAuth

GET /oauth2/authorization/google
→ Google Login
→ /oauth-success
→ Redirect to frontend with JWT

👥 Roles
Role	    Access
USER    	View jobs
ADMIN	    Create, Edit, Delete jobs

📡 API Endpoints
Public
Method	Endpoint
POST	  /register
POST	  /login
GET	    /oauth2/**
GET     /oauth-success

Protected
Method	 Endpoint
GET	     /jobs
POST	   /job
PUT	     /job/{id}
DELETE	 /job/{id}

Admin Only
Method	Endpoint
ALL	   /admin/**

🌍 Live Backend
https://job-portal-backend-o6do.onrender.com
