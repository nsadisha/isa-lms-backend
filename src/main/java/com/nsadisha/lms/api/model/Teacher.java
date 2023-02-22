package com.nsadisha.lms.api.model;

import jakarta.persistence.*;
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
public class Teacher extends User{

    @OneToMany(mappedBy = "conductor", cascade = CascadeType.ALL)
    private Set<Course> conducting_courses;

    public Teacher(User user) {
        super(user);
    }
}
