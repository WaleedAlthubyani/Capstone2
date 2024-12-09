package org.example.fitnesstrackingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please enter a name")
    @Column(columnDefinition = "varchar(255) not null")
    private String name;

    @NotEmpty(message = "Please enter a description")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;
}
