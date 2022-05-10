# Another repository to learn Microservices

Repository created to learn integration of microservices implemented with `Spring Boot` and `Express.js` using asynchronous communication using
`RabbitMQ` and `HTTP`.

![Application Diagram](.github/application-diagram.png)

# Run

To run all applications use ```docker-compose up -d --build``` or use pre-configured xml (IntelliJ) files in `.run` folder.

# Microservices & Applications

| Application | Type           | Version |          URI           | Port  |
|-------------|----------------|---------|:----------------------:|-------|
| product-api | `Spring Boot`  | 2.6.x   | `/api/product-service` | 8080  |
| product-db  | `Postgres`     | 11.x    |           -            | 35433 |
| sales-api   | `Node/Express` | 16.14.x |  `/api/sales-service`  | 8081  |
| sales-db    | `MongoDB`      | 5.x     |           -            | 27017 | 
| sales-queue | `RabbitMQ`     | 3       |           -            | 5672  |
| auth-api    | `Node/Express` | 16.14.x |  `/api/auth-service`   | 8082  |
| auth-db     | `Postgres`     | 11.x    |           -            | 35432 |
