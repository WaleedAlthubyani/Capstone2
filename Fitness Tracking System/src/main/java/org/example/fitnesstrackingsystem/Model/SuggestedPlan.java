package org.example.fitnesstrackingsystem.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "(status='pending' or status='accepted' or status='rejected')")
public class SuggestedPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Please enter a workoutPlan ID")
    @Column(columnDefinition = "int not null")
    private Integer workoutPlanId;

    @NotNull(message = "Please enter instructor ID")
    @Column(columnDefinition = "int not null")
    private Integer instructorId;

    @NotNull(message = "Please enter user ID")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp")
    private LocalDateTime suggestedAt;

    @Pattern(regexp = "(?i)(pending|accepted|rejected)",message = "Status could only be pending, accepted or rejected")
    @Column(columnDefinition = "varchar(8) not null")
    private String status="Pending";

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(columnDefinition = "timestamp null")
    private LocalDateTime decidedOn=null;
}
