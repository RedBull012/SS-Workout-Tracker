# SS Workout Tracker — Backend

A REST API for the Starting Strength workout tracker app, built with Spring Boot and PostgreSQL.

## Tech Stack

- **Java 21**
- **Spring Boot 4**
- **Spring Security** — JWT-based authentication
- **Spring Data JPA** — data access layer
- **PostgreSQL** — relational database
- **Hibernate** — ORM

## Features

- JWT authentication (register/login)
- Workout CRUD — create, read, delete workouts
- Exercise logging — log sets, reps, and weight per workout
- User-scoped data — each user only sees their own workouts

## API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/login` | Login and receive a JWT token |

### Workouts
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/workouts` | Get all workouts for the logged-in user |
| POST | `/api/workouts` | Create a new workout |
| DELETE | `/api/workouts/{id}` | Delete a workout |

### Exercises
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/exercises` | Get all available exercises |
| GET | `/api/workouts/{id}/exercises` | Get exercises logged for a workout |
| POST | `/api/workouts/{workoutId}/exercises/{exerciseId}` | Log an exercise to a workout |
| DELETE | `/api/workouts/exercises/{id}` | Remove an exercise from a workout |

## Local Setup

### Prerequisites
- Java 21
- PostgreSQL
- Maven

### 1. Clone the repo
```bash
git clone https://github.com/yourusername/StartingStrength.git
cd StartingStrength
```

### 2. Create the database
```sql
CREATE DATABASE ss_db;
```

### 3. Configure application.properties
Create `src/main/resources/application.properties` (this file is gitignored — never commit it):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ss_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
jwt.secret=your_jwt_secret_32_chars_minimum
spring.jpa.hibernate.ddl-auto=update
server.port=${PORT:8080}
```

Generate a secure JWT secret:
```bash
openssl rand -base64 32
```

### 4. Run the app
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

## Deployment

This app is deployed on **Railway**. Environment variables are configured in the Railway dashboard:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `JWT_SECRET`
- `PORT`

## Frontend

The React frontend for this project lives at [StartingStrengthFrontend](https://github.com/yourusername/StartingStrengthFrontend) and is deployed on Vercel.
