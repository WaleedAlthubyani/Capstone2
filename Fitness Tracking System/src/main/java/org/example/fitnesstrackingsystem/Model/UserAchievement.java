package org.example.fitnesstrackingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class UserAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Please enter achievement ID")
    @Column(columnDefinition = "int not null")
    private Integer achievementId;

    @NotNull(message = "Please enter achievement ID")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @CreationTimestamp
    @Column(columnDefinition = "date")
    private LocalDate receivedAt;
}
