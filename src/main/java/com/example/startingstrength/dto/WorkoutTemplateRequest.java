package com.example.startingstrength.dto;

import java.util.List;

public record WorkoutTemplateRequest(String name, List<TemplateExerciseRequest> exercises) {}
