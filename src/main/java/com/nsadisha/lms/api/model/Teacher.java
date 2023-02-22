package com.nsadisha.lms.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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
    @JsonIgnore
    private Set<Course> conductingCourses = new HashSet<>();

    public Teacher(User user) {
        super(user);
    }
}
