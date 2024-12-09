package org.example.fitnesstrackingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "(role='trainee' or role='admin')")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please enter a name")
    @Column(columnDefinition = "varchar(100) not null")
    private String name;

    @NotEmpty(message = "Please enter an email")
    @Email(message = "Please enter a valid email address")
    @Column(columnDefinition = "varchar(100) not null unique")
    private String email;

    @NotEmpty(message = "Please enter a password")
    @Size(min = 8,message = "Password can't be less than 8 characters in length")
    @Column(columnDefinition = "varchar(100) not null")
    private String password;

    @Pattern(regexp = "^(?i)(trainee|admin)$",message = "Role can only be trainee or admin")
    @Column(columnDefinition = "varchar(7) not null")
    private String role="trainee";

    @CreationTimestamp
    @Column(columnDefinition = "date")
    private LocalDate createdAt;
}
