package com.example.startingstrength.repositories;

import com.example.startingstrength.models.BodyMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyMetricRepo extends JpaRepository<BodyMetric, Long> {
    List<BodyMetric> findByUserUsernameOrderByRecordedAtDesc(String username);
}
