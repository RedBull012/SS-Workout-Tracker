package com.example.startingstrength.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AnyDiscriminator;

import java.util.List;

@Entity
@Table(name = "workout_exercise")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workoutExerciseId;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    @JsonBackReference
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @Column(name = "sets")
    @Min(value = 1, message = "Set must be at least 1")
    private Integer sets;

    @Column(name = "reps")
    @Min(value = 1, message = "Reps must be at least 1")
    private Integer reps;

    @Column(name = "weight")
    @DecimalMin(value = "0.1", message = "Weight must be greater than 0")
    private Double weight;

    @Column(name = "notes")
    private String notes;

}


