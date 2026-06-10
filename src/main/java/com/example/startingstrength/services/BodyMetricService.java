package com.example.startingstrength.services;

import com.example.startingstrength.dto.BodyMetricRequest;
import com.example.startingstrength.dto.BodyMetricResponse;
import com.example.startingstrength.exceptions.ResourceNotFoundException;
import com.example.startingstrength.models.BodyMetric;
import com.example.startingstrength.models.User;
import com.example.startingstrength.repositories.BodyMetricRepo;
import com.example.startingstrength.repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BodyMetricService {

    private final BodyMetricRepo bodyMetricRepo;
    private final UserRepo userRepo;

    public BodyMetricService(BodyMetricRepo bodyMetricRepo, UserRepo userRepo) {
        this.bodyMetricRepo = bodyMetricRepo;
        this.userRepo = userRepo;
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<BodyMetricResponse> getAllMetrics() {
        return bodyMetricRepo.findByUserUsernameOrderByRecordedAtDesc(getCurrentUser().getUsername())
                .stream()
                .map(BodyMetricResponse::from)
                .toList();
    }

    public BodyMetricResponse addMetric(BodyMetricRequest request) {
        BodyMetric metric = new BodyMetric();
        metric.setUser(getCurrentUser());
        metric.setWeight(request.weight());
        metric.setHeightFeet(request.heightFeet());
        metric.setHeightInches(request.heightInches());
        metric.setRecordedAt(request.recordedAt() != null ? request.recordedAt() : LocalDate.now());
        return BodyMetricResponse.from(bodyMetricRepo.save(metric));
    }

    public void deleteMetric(Long id) {
        bodyMetricRepo.deleteById(id);
    }
}
