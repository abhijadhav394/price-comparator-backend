# Price Comparator Backend

Spring Boot backend for an Amazon vs Flipkart Price Comparison application.

## Features

- User registration and login
- BCrypt password hashing
- JWT authentication
- Role-based authorization
- Product CRUD operations
- Product search
- Product sorting
- Price filtering
- Price range filtering
- Category filtering
- Amazon vs Flipkart price comparison
- Product pagination
- PostgreSQL database
- REST APIs
- CORS configuration

## Product Operations

- Add a new product
- Get all products
- Get product by ID
- Update product
- Delete product
- Search products by name
- Sort products by price
- Filter products by maximum price
- Filter products by price range
- Filter products by category
- Compare Amazon and Flipkart prices
- Find products below a specific price
- Paginate products

## Example Products

| Product | Amazon Price | Flipkart Price | Category |
|---|---:|---:|---|
| iPhone 15 | ₹70,000 | ₹68,500 | Electronics |
| Nike Shoes | ₹5,000 | ₹4,800 | Fashion |

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT
- PostgreSQL
- Maven

## Project Architecture

Controller  
↓  
Service  
↓  
Repository  
↓  
Hibernate / JPA  
↓  
PostgreSQL

## Authentication Flow

User Login  
↓  
Spring Boot  
↓  
Password Verification  
↓  
JWT Token  
↓  
Client  
↓  
Authorization: Bearer Token  
↓  
JWT Filter  
↓  
Protected API

## API Endpoints

### Authentication

```text
POST /users/register
POST /users/login

Products
GET    /products
GET    /products/{id}
POST   /products
PUT    /products/{id}
DELETE /products/{id}

GET /products/search?name=iPhone
GET /products/sort?order=asc
GET /products/filter?maxPrice=50000
GET /products/filter/range?minPrice=20000&maxPrice=60000
GET /products/category/Electronics
GET /products/page?page=0&size=5
GET /products/{id}/compare
GET /products/below-price?price=50000
