package com.nsadisha.lms.api.sample;

import com.nsadisha.lms.api.model.Role;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/
@Component
@RequiredArgsConstructor
public class SampleData {
    private final UserService userService;

    @PostConstruct
    private void addSampleUsers() {
        userService.saveUser(
                new User(0, "Sadisha", "Nimsara", "nsadisha@gmail.com", "123123", Role.STUDENT)
        );
        userService.saveUser(
                new User(0, "Malshani", "Dahanayake", "malshani@gmail.com", "123123", Role.STUDENT)
        );
        userService.saveUser(
                new User(0, "Pubudu", "Wickramathunga", "pubudu@gmail.com", "123123", Role.STUDENT)
        );
    }
}
