package com.example.startingstrength.repositories;

import com.example.startingstrength.models.WorkoutTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutTemplateRepo extends JpaRepository<WorkoutTemplate, Long> {
    List<WorkoutTemplate> findByUserUsername(String username);
}
