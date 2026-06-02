package com.example.startingstrength.models;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "exercise")
@ToString()
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long id;

    @Column(name = "name",  nullable = false)
    private String name;
}
