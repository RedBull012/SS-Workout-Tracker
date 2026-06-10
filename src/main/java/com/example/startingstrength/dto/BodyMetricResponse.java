package com.example.startingstrength.dto;

import com.example.startingstrength.models.BodyMetric;

import java.time.LocalDate;

public record BodyMetricResponse(
        Long id,
        Double weight,
        Integer heightFeet,
        Integer heightInches,
        LocalDate recordedAt
) {
    public static BodyMetricResponse from(BodyMetric m) {
        return new BodyMetricResponse(
                m.getId(),
                m.getWeight(),
                m.getHeightFeet(),
                m.getHeightInches(),
                m.getRecordedAt()
        );
    }
}
