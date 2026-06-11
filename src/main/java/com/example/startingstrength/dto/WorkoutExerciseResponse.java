package com.example.startingstrength.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WorkoutExerciseResponse {
    private Long id;
    private Long exerciseId;
    private String exerciseName;
    private Integer sets;
    private Integer reps;
    private Double weight;
    private String notes;
    private LocalDate date;
}
