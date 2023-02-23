package com.nsadisha.lms.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Sadisha Nimsara
 * @created 01 of Feb 2023
 **/
@Entity
@Data
@NoArgsConstructor
public class Student extends User{
    public Student(User user) {
        super(user);
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    @JsonIgnore
    public Set<StudentCourseRegistration> registrations = new HashSet<>();

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
