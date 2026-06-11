package com.example.startingstrength.repositories;

import com.example.startingstrength.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkoutRepo extends JpaRepository<Workout, Long> {
    List<Workout> findByUserUsername(String username);
    List<Workout> findByUserUsernameAndDateBetweenOrderByDateAsc(String username, LocalDate startDate, LocalDate endDate);
}
