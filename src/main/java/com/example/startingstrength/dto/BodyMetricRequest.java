package com.example.startingstrength.dto;

import java.time.LocalDate;

public record BodyMetricRequest(
        Double weight,
        Integer heightFeet,
        Integer heightInches,
        LocalDate recordedAt
) {}
