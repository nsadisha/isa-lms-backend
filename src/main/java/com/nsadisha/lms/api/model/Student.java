package com.nsadisha.lms.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Sadisha Nimsara
 * @created 01 of Feb 2023
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Student extends User{
    public Student(User user) {
        super(user);
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private Set<StudentCourseRegistration> registrations;
}
