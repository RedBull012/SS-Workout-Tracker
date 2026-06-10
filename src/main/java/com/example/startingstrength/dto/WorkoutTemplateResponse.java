package com.example.startingstrength.dto;

import com.example.startingstrength.models.WorkoutTemplate;

import java.util.List;

public record WorkoutTemplateResponse(
        Long id,
        String name,
        List<TemplateExerciseResponse> exercises
) {
    public record TemplateExerciseResponse(Long id, Long exerciseId, String exerciseName, Integer sets, Integer reps) {}

    public static WorkoutTemplateResponse from(WorkoutTemplate t) {
        List<TemplateExerciseResponse> exList = t.getExercises().stream()
                .map(e -> new TemplateExerciseResponse(
                        e.getId(),
                        e.getExercise().getId(),
                        e.getExercise().getName(),
                        e.getSets(),
                        e.getReps()))
                .toList();
        return new WorkoutTemplateResponse(t.getId(), t.getName(), exList);
    }
}
