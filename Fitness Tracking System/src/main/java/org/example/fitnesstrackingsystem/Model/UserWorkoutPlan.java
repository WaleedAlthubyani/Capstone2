package org.example.fitnesstrackingsystem.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "(status='not started' or status= 'in progress' or status='completed')")
public class UserWorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int not null")
    private Integer userId;

    @Column(columnDefinition = "int not null")
    private Integer workoutPlanId;

    @Pattern(regexp = "^(?i)(not started|in progress|complete)$",message = "status can only be not started, in progress or complete")
    @Column(columnDefinition = "varchar(11) not null")
    private String status="not started";


    @Column(columnDefinition = "int not null")
    private Integer exercises;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(columnDefinition = "int not null")
    private Integer completed=0;
}
