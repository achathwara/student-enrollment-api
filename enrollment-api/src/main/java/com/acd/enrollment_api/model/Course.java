package com.acd.enrollment_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int credits;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToMany(mappedBy = "enrolledCourses")
    @JsonIgnore // Prevents infinite recursion when converting to JSON
    private Set<Student> students = new HashSet<>();
}
