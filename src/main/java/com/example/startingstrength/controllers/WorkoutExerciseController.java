package com.example.startingstrength.controllers;


import com.example.startingstrength.dto.WorkoutExerciseRequest;
import com.example.startingstrength.dto.WorkoutExerciseResponse;
import com.example.startingstrength.services.WorkoutExerciseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutExerciseController {

    private final WorkoutExerciseService service;

    public WorkoutExerciseController(WorkoutExerciseService service) {
        this.service = service;
    }

    @GetMapping("/exercises/{id}")
    public ResponseEntity<WorkoutExerciseResponse> getWorkoutExerciseById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getWorkoutExerciseById(id));
    }

    @PostMapping("/{workoutId}/exercises/{exerciseId}")
    public ResponseEntity<WorkoutExerciseResponse> createWorkoutExercise(
            @PathVariable Long workoutId,
            @PathVariable Long exerciseId,
            @Valid @RequestBody WorkoutExerciseRequest request) {
        WorkoutExerciseResponse created = service.createWorkoutExercise(workoutId, exerciseId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/exercises/{id}")
    public ResponseEntity<WorkoutExerciseResponse> updateWorkoutExercise(
            @PathVariable Long id,
            @Valid @RequestBody WorkoutExerciseRequest request) {
        WorkoutExerciseResponse updated = service.updateWorkoutExercise(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/exercises/{id}")
    public ResponseEntity<Void> deleteWorkoutExercise(@PathVariable Long id) {
        service.deleteWorkoutExercise(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{workoutId}/exercises")
    public ResponseEntity<List<WorkoutExerciseResponse>> getExercisesForWorkout(@PathVariable Long workoutId) {
        return ResponseEntity.ok(service.getExercisesForWorkout(workoutId));
    }

    @GetMapping("/exercises/{exerciseId}/progress")
    public ResponseEntity<List<WorkoutExerciseResponse>> getProgressForExercise(@PathVariable Long exerciseId) {
        return ResponseEntity.ok(service.getProgressForExercise(exerciseId));
    }
}
