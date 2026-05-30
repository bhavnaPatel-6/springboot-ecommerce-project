# Spring Boot E-Commerce Application

## Overview

This is a full-stack E-Commerce application built using Spring Boot, Spring Security, JWT Authentication, MySQL, HTML, CSS, and JavaScript.

The project provides secure user authentication, product management, image upload functionality, and frontend integration.

---

## Features

### Authentication & Security

* User Registration
* User Login
* JWT Authentication
* Spring Security Integration
* Password Encryption using BCrypt

### Product Management

* Add Product
* Update Product
* Delete Product
* View Products
* Product Search

### Image Management

* Upload Product Images
* Store Image Path in Database
* Display Images on Frontend

### Frontend

* HTML
* CSS
* JavaScript
* Fetch API Integration

---

## Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* JWT
* Maven

### Database

* MySQL

### Frontend

* HTML
* CSS
* JavaScript

---

## Project Structure

Controller Layer
→ Handles HTTP Requests

Service Layer
→ Contains Business Logic

Repository Layer
→ Handles Database Operations

Database
→ MySQL

---

## API Endpoints

### Authentication

POST /api/auth/register

POST /api/auth/login

### Products

GET /api/products

POST /api/product

PUT /api/product/{id}

DELETE /api/product/{id}

---

## Security Flow

User Login
→ JWT Token Generated

Frontend Stores Token
→ Local Storage

Frontend Sends Token
→ Authorization Header

Spring Security + JWT Filter
→ Validates Token

Access Granted
→ Protected APIs

---

## Future Enhancements

* Category Module
* Cart Management
* Order Placement
* Order History
* Role-Based Authorization (Admin/User)
* Pagination & Sorting
* Product Filters

---

## Author

Bhavna Patel

B.Tech CSE Student

Learning Spring Boot, Java, DSA, REST APIs, and Full-Stack Development.
