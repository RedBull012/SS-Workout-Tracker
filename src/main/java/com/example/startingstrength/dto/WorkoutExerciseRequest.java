package com.example.startingstrength.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutExerciseRequest {
    @Min(value = 1, message = "Sets must be at least 1")
    private Integer sets;

    @Min(value = 1, message = "Reps must be at least 1")
    private Integer reps;

    @DecimalMin(value = "0.1", message = "Weight must be greater than 0")
    private Double weight;

    private String notes;
}
