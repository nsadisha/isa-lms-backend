package com.nsadisha.lms.api.model;

import org.springframework.stereotype.Service;

/**
 * @author Sadisha Nimsara
 * @created 01 of Feb 2023
 **/
@Service
public class UserFactory {
    public User getInstance(User user) {
        switch (user.getRole()) {
            case STUDENT -> {
                return new Student(user);
            }
            case TEACHER -> {
                return new Teacher(user);
            }
            case MANAGEMENT_STAFF -> {
                return new ManagementStaff(user);
            }
            default -> {
                return user;
            }
        }
    }
}
