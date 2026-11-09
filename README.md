# Store - E-commerce REST API
A simple backend application for an online store, built with Java 21 and Spring Boot. 
The project provides a RESTful API and uses PostgreSQL for data persistence.

## Tech Stack
- Programming Language: Java 21 
- Framework: Spring Boot 4.1.1
- Security: Spring Security, JWT (JSON Web Tokens)
- Data Access: Spring Data JPA, Hibernate
- Build Tools: Maven
- Version control: Git

## Features
- RESTful API architecture
- User authentication and authorization
- JWT-based authentication
- Role-based access control
- Product and category management
- Shopping cart management
- Order management
- Input validation
- Centralized exception handling
- Relational database persistence

## Prerequisites
- JDK 21 or later
- Maven
- postgresql
- Git

## Architecture Overview
The project follows a layered architecture:

- Controller: Handles incoming HTTP requests and delegates tasks to the Service layer.
- Service: Contains the core business logic of the application.
- Repository: Manages data access operations, interacting with the database.


## Authentication Flow Details

1. Registration: Users submit their details to create a new account.
2. Login: Users provide credentials (email/password). Upon successful validation, an Access Token is issued. The Access Token is returned in the response body.
3. Accessing Protected Resources: Requests to secured API endpoints must include the Access Token in the Authorization header.


## API Endpoint List
### Authentication (`/api/v1/auth`)

- `POST /register`: Register a new user.
- `POST /login`: User login and token generation.

### Categories (`/api/v1/categories)

- `POST`: Create a new category. (Details to be confirmed from code)

### Products (/api/v1/products)

- `POST`: Add a new product.
- `GET`: Retrieve a list of all products.
- `PATCH /{id}/quantity`: Update product stock quantity.

### Cart (/api/v1/cart)

- `POST /items`: Add a product to the cart.
- `GET`: Retrieve the contents of a cart.
- `DELETE /items/{productId}`: Remove a specific product from the cart.
- `DELETE /items`: Clear the entire cart.

### Orders (/api/v1/orders)

- `GET`: Retrieve all orders for the current user.

### Checkout (/api/v1/checkout)

- `GET`: Process and finalize an order.