package com.nsadisha.lms.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Sadisha Nimsara
 * @created 21 of Feb 2023
 **/
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    @JsonProperty("course_code")
    private String courseCode;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    private Teacher conductor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    @JsonIgnore
    public Set<StudentCourseRegistration> registrations = new HashSet<>();

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
