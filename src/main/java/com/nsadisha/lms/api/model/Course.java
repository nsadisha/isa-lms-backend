package com.nsadisha.lms.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sadisha Nimsara
 * @created 21 of Feb 2023
 **/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String course_code;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    private Teacher conductor;

}
