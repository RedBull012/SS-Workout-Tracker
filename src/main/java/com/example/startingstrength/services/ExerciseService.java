package com.example.startingstrength.services;


import com.example.startingstrength.exceptions.ResourceNotFoundException;
import com.example.startingstrength.models.Exercise;
import com.example.startingstrength.models.User;
import com.example.startingstrength.repositories.ExerciseRepo;
import com.example.startingstrength.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepo exerciseRepo;

    public ExerciseService(ExerciseRepo exerciseRepo) {
        this.exerciseRepo = exerciseRepo;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepo.findAll();
    }

    public Exercise getExerciseById(Long id) {
        return exerciseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise by id " + id + " not found"));
    }

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepo.save(exercise);
    }

    public void deleteExercise(Long id) {
        exerciseRepo.deleteById(id);
    }

    public Exercise updateExercise(Long id, Exercise updatedExercise) {
        Exercise existing = exerciseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found"));
        existing.setName(updatedExercise.getName());
        return exerciseRepo.save(existing);
    }

    @Service
    public static class UserService implements UserDetailsService {

        private final UserRepo userRepo;
        private final PasswordEncoder passwordEncoder;

        public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
            this.userRepo = userRepo;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(List.of())
                    .build();
        }

        public User registerUser(String username, String password) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            return userRepo.save(user);
        }
    }
}
