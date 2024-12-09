package org.example.fitnesstrackingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class InstructorStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Please enter instructor ID")
    @Column(columnDefinition = "int not null")
    private Integer instructorId;

    @NotNull(message = "Please enter instructor ID")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "Please enter user workout plan Id")
    private Integer userWorkoutPlanId;
}
