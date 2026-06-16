package com.example.startingstrength.controllers;

import com.example.startingstrength.dto.WorkoutExerciseResponse;
import com.example.startingstrength.dto.WorkoutTemplateRequest;
import com.example.startingstrength.dto.WorkoutTemplateResponse;
import com.example.startingstrength.services.WorkoutTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class WorkoutTemplateController {

    private final WorkoutTemplateService service;

    public WorkoutTemplateController(WorkoutTemplateService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<WorkoutTemplateResponse>> getAllTemplates() {
        return ResponseEntity.ok(service.getAllTemplates());
    }

    @PostMapping
    public ResponseEntity<WorkoutTemplateResponse> createTemplate(@RequestBody WorkoutTemplateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTemplate(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) {
        service.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{templateId}/apply/{workoutId}")
    public ResponseEntity<List<WorkoutExerciseResponse>> applyTemplate(
            @PathVariable Long templateId,
            @PathVariable Long workoutId) {
        return ResponseEntity.ok(service.applyTemplate(workoutId, templateId));
    }
}
