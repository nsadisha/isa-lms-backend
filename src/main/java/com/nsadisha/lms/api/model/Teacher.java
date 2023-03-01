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
public class Teacher extends User{

    @OneToMany(mappedBy = "conductor", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Course> conductingCourses = new HashSet<>();

    public Teacher(User user) {
        super(user);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
