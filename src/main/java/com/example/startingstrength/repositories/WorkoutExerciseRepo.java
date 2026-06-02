package com.example.startingstrength.repositories;

import com.example.startingstrength.models.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutExerciseRepo extends JpaRepository<WorkoutExercise, Long> {

        List<WorkoutExercise> findByWorkoutId(Long workoutId);

        @Query("SELECT we FROM WorkoutExercise we WHERE we.exercise.id = :exerciseId ORDER BY we.workout.date ASC")
        List<WorkoutExercise> findByExerciseIdOrderByDate(@Param("exerciseId") Long exerciseId);
}
