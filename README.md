# 🏡 Rental App -- Backend API

API REST de gestion de locations immobilières\
*Projet 3 -- OpenClassrooms*

## 🚀 Technologies utilisées

-   Java 17
-   Spring Boot 4
-   Spring Web MVC
-   Spring Data JPA
-   Spring Security + JWT
-   PostgreSQL
-   Hibernate
-   Lombok
-   Springdoc / Swagger OpenAPI 3
-   Maven / mvnd
-   Upload de fichiers

## 📦 Fonctionnalités principales

### 🔐 Authentification & Utilisateurs

-   Inscription
-   Connexion + JWT
-   Sécurisation des endpoints
-   Gestion de l'utilisateur connecté

### 🏠 Gestion des Annonces (Rentals)

-   CRUD annonces
-   Upload photos
-   Association propriétaire via JWT

### 📬 Messages

-   Envoi de message
-   Listing des messages reçus

### 📘 Documentation Swagger

http://localhost:8080/swagger-ui/index.html

## ⚙️ Installation & Configuration

### 1. Cloner le projet

    git clone https://github.com/<ton-user>/<ton-repo>.git
    cd rental_backend

### 2. Configurer PostgreSQL

Créer une base :

    CREATE DATABASE rental_oc;

Configurer `application.properties` :

    spring.datasource.url=jdbc:postgresql://localhost:5432/rental_oc
    spring.datasource.username=postgres
    spring.datasource.password=TON_MDP

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    spring.web.resources.static-locations=file:uploads/
    spring.servlet.multipart.enabled=true
    spring.servlet.multipart.max-file-size=10MB
    spring.servlet.multipart.max-request-size=10MB

### 3. Lancer l'application

    mvnd clean package -DskipTests
    mvnd spring-boot:run

## 📚 Endpoints principaux

### Auth

POST /api/auth/register\
POST /api/auth/login

### Rentals

GET /api/rentals\
GET /api/rentals/{id}\
POST /api/rentals\
PUT /api/rentals/{id}

### Messages

POST /api/messages\
GET /api/messages

## 🔐 JWT

Ajouter dans chaque requête protégée :

    Authorization: Bearer <token>

## 🧩 Architecture

src/main/java/com/openclassrooms/rental/\
│── config/\
│── controllers/\
│── dto/\
│── model/\
│── repository/\
│── services/

## 📜 Licence

Libre d'utilisation pour OpenClassrooms.
