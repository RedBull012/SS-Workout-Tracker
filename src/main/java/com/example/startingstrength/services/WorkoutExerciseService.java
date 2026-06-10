package com.example.startingstrength.services;


import com.example.startingstrength.dto.WorkoutExerciseRequest;
import com.example.startingstrength.dto.WorkoutExerciseResponse;
import com.example.startingstrength.exceptions.ResourceNotFoundException;
import com.example.startingstrength.models.Exercise;
import com.example.startingstrength.models.Workout;
import com.example.startingstrength.models.WorkoutExercise;
import com.example.startingstrength.repositories.ExerciseRepo;
import com.example.startingstrength.repositories.WorkoutExerciseRepo;
import com.example.startingstrength.repositories.WorkoutRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutExerciseService {

    private final WorkoutRepo workoutRepo;
    private final ExerciseRepo exerciseRepo;
    private final WorkoutExerciseRepo workoutExerciseRepo;

    public WorkoutExerciseService(WorkoutExerciseRepo workoutExerciseRepo,
                                  WorkoutRepo workoutRepo,
                                  ExerciseRepo exerciseRepo) {
        this.workoutExerciseRepo = workoutExerciseRepo;
        this.workoutRepo = workoutRepo;
        this.exerciseRepo = exerciseRepo;
    }

    private WorkoutExerciseResponse toResponse(WorkoutExercise workoutExercise) {
        WorkoutExerciseResponse response = new WorkoutExerciseResponse();
        response.setId(workoutExercise.getWorkoutExerciseId());
        response.setExerciseName(workoutExercise.getExercise().getName());
        response.setSets(workoutExercise.getSets());
        response.setReps(workoutExercise.getReps());
        response.setWeight(workoutExercise.getWeight());
        response.setNotes(workoutExercise.getNotes());
        response.setDate(workoutExercise.getWorkout().getDate());
        return response;
    }


   public List<WorkoutExercise> getAllWorkoutExercise(){
        return workoutExerciseRepo.findAll();
   }

    public WorkoutExerciseResponse getWorkoutExerciseById(Long id) {
        return toResponse(workoutExerciseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout exercise by id " + id + " not found")));
    }

   public WorkoutExerciseResponse createWorkoutExercise(Long workoutId, Long exerciseId, WorkoutExerciseRequest request) {
       Workout workout = workoutRepo.findById(workoutId)
               .orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
       Exercise exercise = exerciseRepo.findById(exerciseId)
               .orElseThrow(() -> new ResourceNotFoundException("Exercise not found"));

       WorkoutExercise workoutExercise = new WorkoutExercise();
       workoutExercise.setWorkout(workout);
       workoutExercise.setExercise(exercise);
       workoutExercise.setSets(request.getSets());
       workoutExercise.setReps(request.getReps());
       workoutExercise.setWeight(request.getWeight());
       workoutExercise.setNotes(request.getNotes());
       return toResponse(workoutExerciseRepo.save(workoutExercise));
        }

   public void deleteWorkoutExercise(Long id){
        workoutExerciseRepo.deleteById(id);
   }

   public WorkoutExerciseResponse updateWorkoutExercise(Long id, WorkoutExerciseRequest request){
        WorkoutExercise existing = workoutExerciseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout Exercise not found"));
       existing.setSets(request.getSets());
       existing.setReps(request.getReps());
       existing.setWeight(request.getWeight());
       existing.setNotes(request.getNotes());
       return toResponse(workoutExerciseRepo.save(existing));
   }

    public List<WorkoutExerciseResponse> getExercisesForWorkout(Long workoutId) {
        return workoutExerciseRepo.findByWorkoutId(workoutId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private String currentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public List<WorkoutExerciseResponse> getProgressForExercise(Long exerciseId) {
        return workoutExerciseRepo.findByExerciseIdAndUsernameOrderByDate(exerciseId, currentUsername())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<WorkoutExerciseResponse> getLastSessionForExercise(Long exerciseId) {
        return workoutExerciseRepo.findLastByExerciseIdAndUsername(exerciseId, currentUsername())
                .map(this::toResponse);
    }
}
