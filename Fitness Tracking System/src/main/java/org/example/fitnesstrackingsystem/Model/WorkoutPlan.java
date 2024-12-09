package org.example.fitnesstrackingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please enter a name")
    @Column(columnDefinition = "varchar(100) not null")
    private String name;

    @NotEmpty(message = "Please enter a description")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;

    @NotNull(message = "Please enter the expected start date")
    @FutureOrPresent(message = "start date can only be today or in the future")
    @Column
    private LocalDate startDate;

    @NotNull(message = "Please enter the expected end date")
    @FutureOrPresent(message = "end date can only be today or in the future")
    @Column(columnDefinition = "date not null")
    private LocalDate endDate;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @Null(message = "You can't have an updated Date yet")
    @Column(columnDefinition = "timestamp null")
    private LocalDateTime updatedAt;

}
