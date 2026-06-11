package com.example.startingstrength.controllers;


import com.example.startingstrength.models.Workout;
import com.example.startingstrength.services.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        List<Workout> workouts = workoutService.getAllWorkouts();
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutService.getWorkoutById(id));
    }

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@Valid @RequestBody Workout workout) {
        Workout created = workoutService.createWorkout(workout);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workout> updateWorkout(@Valid @PathVariable Long id, @RequestBody Workout workout) {
        Workout updated = workoutService.updateWorkout(id, workout);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportCsv(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        String csv = workoutService.exportCsv(startDate, endDate);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "workouts_" + startDate + "_to_" + endDate + ".csv");
        return new ResponseEntity<>(csv.getBytes(), headers, HttpStatus.OK);
    }

}
