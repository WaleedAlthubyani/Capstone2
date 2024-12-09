package org.example.fitnesstrackingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "progress>=0 and progress<=100")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please enter a target")
    @Column(columnDefinition = "varchar(255) not null")
    private String target;

    @PositiveOrZero(message = "Progress cant be negative")
    @Max(value = 100,message = "Progress can't exceed 100")
    @Column(columnDefinition = "int")
    private Integer progress=0;

    @NotNull(message = "Please enter expected target completion date")
    @FutureOrPresent(message = "target date can't be in the past")
    @Column(columnDefinition = "date")
    private LocalDate targetDate;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @Null(message = "you can't have an update date yet")
    @Column(columnDefinition = "timestamp null")
    private LocalDateTime updatedAt;

    @NotNull(message = "please enter user ID")
    @Column(columnDefinition = "int not null")
    private Integer userId;
}
