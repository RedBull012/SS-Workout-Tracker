# SS-Workout-Tracker - PLANNING

## Tech Stack
- Language: Java
- Framework: Spring Boot
- Database: Postgres
- Build Tool: Maven
- Version Control: Git/Github

## API Endpoints (Planned)
### Workouts
  - GET /workouts — get all workouts
  - GET /workouts/{id} — get a single workout
  - POST /workouts — create a workout
  - PUT /workouts/{id} — update a workout
  - DELETE /workouts/{id} — delete a workout

### Exercises

  - GET /exercises — get all exercises
  - POST /exercises — add an exercise
  - DELETE /exercises/{id} — delete an exercise

## Data Models

### User
- id
- username
- email
- password (Hashed)
- created_at

### Workout
  - id
  - user_id(Foreign key)
  - name
  - date
  - notes
  - created_at
### Exercise
  - id
  - name

### WorkoutExercise (joins a Workout to an Exercise)
  - id
  - workout_id (foreign key)
  - exercise_id (foreign key)
  - sets
  - reps
  - weight
  - notes


### Exercises
- Squat
- Bench
- Overhead Press
- Dead Lift
- Rows
- Chin Ups
- Power Clean