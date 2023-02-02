package com.nsadisha.lms.api.sample;

import com.nsadisha.lms.api.controller.auth.AuthenticationService;
import com.nsadisha.lms.api.controller.auth.RegisterRequest;
import com.nsadisha.lms.api.model.Role;
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
    private final AuthenticationService authenticationService;

    @PostConstruct
    private void addSampleUsers() {
        authenticationService.register(
                new RegisterRequest(
                        "Sadisha",
                        "Nimsara",
                        "nsadisha@gmail.com",
                        "123123",
                        Role.STUDENT
                )
        );
        authenticationService.register(
                new RegisterRequest(
                        "Malshani",
                        "Dahanayake",
                        "malshani@gmail.com",
                        "123123",
                        Role.TEACHER
                )
        );
        authenticationService.register(
                new RegisterRequest(
                        "Pubudu",
                        "Wickramathunga",
                        "pubudu@gmail.com",
                        "123123",
                        Role.MANAGEMENT_STAFF
                )
        );
    }
}
