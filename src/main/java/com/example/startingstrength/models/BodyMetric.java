package com.example.startingstrength.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "body_metrics")
@Getter
@Setter
@NoArgsConstructor
public class BodyMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height_feet")
    private Integer heightFeet;

    @Column(name = "height_inches")
    private Integer heightInches;

    @Column(name = "recorded_at", nullable = false)
    private LocalDate recordedAt;
}
