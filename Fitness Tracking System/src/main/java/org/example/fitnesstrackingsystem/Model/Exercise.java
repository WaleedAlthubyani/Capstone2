package org.example.fitnesstrackingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "sets>0 and reps>0 and (status='not started' or status= 'in progress' or status='completed')")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please enter a name")
    @Column(columnDefinition = "varchar(100) not null")
    private String name;

    @NotEmpty(message = "Please enter a description")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;

    @Column(columnDefinition = "int null")
    @Positive(message = "Sets must be a positive number")
    private Integer sets;

    @Column(columnDefinition = "int null")
    @Positive(message = "Reps must be a positive number")
    private Integer reps;

    @Column(columnDefinition = "time null")
    private LocalTime duration;

    @Pattern(regexp = "^(?i)(not started|in progress|completed)$")
    @Column(columnDefinition = "varchar(11) not null")
    private String status="not started";

    @NotNull(message = "Please enter a workout plan ID")
    @Column(columnDefinition = "int not null")
    private Integer workoutPlanId;
}
