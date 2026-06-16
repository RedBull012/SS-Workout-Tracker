package com.example.startingstrength.controllers;

import com.example.startingstrength.dto.BodyMetricRequest;
import com.example.startingstrength.dto.BodyMetricResponse;
import com.example.startingstrength.services.BodyMetricService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/metrics")
public class BodyMetricController {
    private final BodyMetricService bodyMetricService;

    public BodyMetricController(BodyMetricService bodyMetricService) {
        this.bodyMetricService = bodyMetricService;
    }

    @GetMapping
    public ResponseEntity<List<BodyMetricResponse>> getAllMetrics() {
        return ResponseEntity.ok(bodyMetricService.getAllMetrics());
    }

    @PostMapping
    public ResponseEntity<BodyMetricResponse> addMetric(@RequestBody BodyMetricRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bodyMetricService.addMetric(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetric(@PathVariable Long id) {
        bodyMetricService.deleteMetric(id);
        return ResponseEntity.noContent().build();
    }
}
