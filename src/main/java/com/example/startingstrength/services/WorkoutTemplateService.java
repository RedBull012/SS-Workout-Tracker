package com.example.startingstrength.services;

import com.example.startingstrength.dto.WorkoutExerciseResponse;
import com.example.startingstrength.dto.WorkoutTemplateRequest;
import com.example.startingstrength.dto.WorkoutTemplateResponse;
import com.example.startingstrength.exceptions.ResourceNotFoundException;
import com.example.startingstrength.models.*;
import com.example.startingstrength.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutTemplateService {

    private final WorkoutTemplateRepo templateRepo;
    private final WorkoutRepo workoutRepo;
    private final WorkoutExerciseRepo workoutExerciseRepo;
    private final ExerciseRepo exerciseRepo;
    private final UserRepo userRepo;

    public WorkoutTemplateService(WorkoutTemplateRepo templateRepo,
                                  WorkoutRepo workoutRepo,
                                  WorkoutExerciseRepo workoutExerciseRepo,
                                  ExerciseRepo exerciseRepo,
                                  UserRepo userRepo) {
        this.templateRepo = templateRepo;
        this.workoutRepo = workoutRepo;
        this.workoutExerciseRepo = workoutExerciseRepo;
        this.exerciseRepo = exerciseRepo;
        this.userRepo = userRepo;
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<WorkoutTemplateResponse> getAllTemplates() {
        return templateRepo.findByUserUsername(getCurrentUser().getUsername())
                .stream()
                .map(WorkoutTemplateResponse::from)
                .toList();
    }

    public WorkoutTemplateResponse createTemplate(WorkoutTemplateRequest request) {
        WorkoutTemplate template = new WorkoutTemplate();
        template.setName(request.name());
        template.setUser(getCurrentUser());

        for (var ex : request.exercises()) {
            Exercise exercise = exerciseRepo.findById(ex.exerciseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Exercise not found: " + ex.exerciseId()));
            WorkoutTemplateExercise te = new WorkoutTemplateExercise();
            te.setTemplate(template);
            te.setExercise(exercise);
            te.setSets(ex.sets());
            te.setReps(ex.reps());
            template.getExercises().add(te);
        }

        return WorkoutTemplateResponse.from(templateRepo.save(template));
    }

    public void deleteTemplate(Long id) {
        templateRepo.deleteById(id);
    }

    public List<WorkoutExerciseResponse> applyTemplate(Long workoutId, Long templateId) {
        Workout workout = workoutRepo.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
        WorkoutTemplate template = templateRepo.findById(templateId)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found"));

        return template.getExercises().stream().map(te -> {
            WorkoutExercise we = new WorkoutExercise();
            we.setWorkout(workout);
            we.setExercise(te.getExercise());
            we.setSets(te.getSets());
            we.setReps(te.getReps());
            we.setWeight(null);
            WorkoutExercise saved = workoutExerciseRepo.save(we);

            WorkoutExerciseResponse r = new WorkoutExerciseResponse();
            r.setId(saved.getWorkoutExerciseId());
            r.setExerciseName(saved.getExercise().getName());
            r.setSets(saved.getSets());
            r.setReps(saved.getReps());
            r.setWeight(saved.getWeight());
            r.setDate(workout.getDate());
            return r;
        }).toList();
    }
}
