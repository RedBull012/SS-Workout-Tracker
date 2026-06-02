package com.example.startingstrength.repositories;

import com.example.startingstrength.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, Long> {
}
