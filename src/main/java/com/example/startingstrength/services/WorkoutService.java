package com.example.startingstrength.services;

import com.example.startingstrength.exceptions.ResourceNotFoundException;
import com.example.startingstrength.models.User;
import com.example.startingstrength.models.Workout;
import com.example.startingstrength.repositories.UserRepo;
import com.example.startingstrength.repositories.WorkoutRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepo workoutRepository;
    private final UserRepo userRepo;

    public WorkoutService(WorkoutRepo workoutRepository, UserRepo userRepo) {
        this.workoutRepository = workoutRepository;
        this.userRepo = userRepo;
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findByUserUsername(getCurrentUser().getUsername());
    }

    public Workout getWorkoutById(Long id) {
        return workoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout with id " + id + " not found"));
    }

    public Workout createWorkout(Workout workout) {
        workout.setUser(getCurrentUser());
        return workoutRepository.save(workout);
    }

    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }

    public Workout updateWorkout(Long id, Workout updatedWorkout) {
        Workout existing = workoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout with id " + id + " not found"));
        existing.setName(updatedWorkout.getName());
        existing.setDate(updatedWorkout.getDate());
        existing.setNotes(updatedWorkout.getNotes());
        return workoutRepository.save(existing);
    }
}