package org.example.fitnesstrackingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "(status='inactive' or status='active')")
public class Instructor {

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
    private String password;

    @NotEmpty(message = "Please enter a certificate url")
    @URL(message = "Please enter a valid url")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String certificateURL;

    @Column(columnDefinition = "varchar(8) not null")
    private String status="inactive";

    @CreationTimestamp
    @Column(columnDefinition = "timestamp")
    private LocalDateTime createdAt;
}
