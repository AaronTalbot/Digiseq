# Organisation Admin Portal

This project is a web-based administration portal for managing client organizations and their associated personnel. The back-end is built using Java with Spring Boot, and the front-end is a simple HTML/JavaScript interface. The back-end exposes RESTful APIs for CRUD operations on client organizations and personnel records.

## Features

- View, create, edit, update, and delete client organizations.
- View, create, edit, update, and delete personnel records associated with client organizations.
- Basic user authentication and CSRF protection.
- RESTful APIs for all back-end functionality.
- Simple front-end interface for interacting with the back-end APIs.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- HTML/CSS/JavaScript

## Getting Started

### Prerequisites

- Java 17 or later
- Maven
- PostgreSQL

### Setup

1. **Clone the repository**:
    ```sh
    git clone https://github.com/aarontalbot/digisec-admin-portal.git
    cd digisec-admin-portal
    ```

2. **Configure PostgreSQL**:
    - Create a database named `digisecdb`.
    - Update the `src/main/resources/application.properties` file with your PostgreSQL username and password:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/digisecdb
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build and run the application**:
    ```sh
    mvn spring-boot:run
    ```

4. **Access the application**:
    - Open your web browser and go to `http://localhost:8080/index.html`.

## API Endpoints

### Client Organisations

- `GET /api/client-organisations`: Get all client organizations.
- `POST /api/client-organisations`: Create a new client organization.
- `GET /api/client-organisations/{id}`: Get a client organization by ID.
- `PUT /api/client-organisations/{id}`: Update a client organization by ID.
- `DELETE /api/client-organisations/{id}`: Delete a client organization by ID.

### Personnel

- `GET /api/personnel`: Get all personnel.
- `POST /api/personnel`: Create a new personnel.
- `GET /api/personnel/{id}`: Get personnel by ID.
- `PUT /api/personnel/{id}`: Update personnel by ID.
- `DELETE /api/personnel/{id}`: Delete personnel by ID.

## Testing

To run the tests, use the following command:

```sh
mvn test
