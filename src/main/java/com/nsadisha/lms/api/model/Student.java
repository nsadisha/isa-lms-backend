package com.nsadisha.lms.api.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
}
