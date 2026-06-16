package com.example.startingstrength.repositories;

import com.example.startingstrength.models.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutExerciseRepo extends JpaRepository<WorkoutExercise, Long> {

        List<WorkoutExercise> findByWorkoutId(Long workoutId);

        @Query("SELECT we FROM WorkoutExercise we WHERE we.exercise.id = :exerciseId AND we.workout.user.username = :username ORDER BY we.workout.date ASC")
        List<WorkoutExercise> findByExerciseIdAndUsernameOrderByDate(@Param("exerciseId") Long exerciseId, @Param("username") String username);

        @Query("SELECT we FROM WorkoutExercise we WHERE we.exercise.id = :exerciseId AND we.workout.user.username = :username AND we.weight IS NOT NULL ORDER BY we.workout.date DESC LIMIT 1")
        Optional<WorkoutExercise> findLastByExerciseIdAndUsername(@Param("exerciseId") Long exerciseId, @Param("username") String username);
}
