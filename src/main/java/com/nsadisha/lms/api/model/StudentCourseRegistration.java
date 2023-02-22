package com.nsadisha.lms.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

/**
 * @author Sadisha Nimsara
 * @created 22 of Feb 2023
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @CreatedDate
    private Date registrationDate;

    private float marks;
}
