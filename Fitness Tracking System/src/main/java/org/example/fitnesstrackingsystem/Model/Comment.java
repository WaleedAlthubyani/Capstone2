package org.example.fitnesstrackingsystem.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please enter a content")
    @Column(columnDefinition = "varchar(255) not null")
    private String content;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(columnDefinition = "timestamp null")
    private LocalDateTime updatedAt = null;

    @NotNull(message = "Please enter a user ID")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "Please enter a post ID")
    @Column(columnDefinition = "int not null")
    private Integer postId;
}
