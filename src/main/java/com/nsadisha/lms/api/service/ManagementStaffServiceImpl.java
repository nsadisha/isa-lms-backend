package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.controller.auth.AuthenticationResponse;
import com.nsadisha.lms.api.controller.auth.AuthenticationService;
import com.nsadisha.lms.api.controller.auth.RegisterRequest;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 08 of Feb 2023
 **/
@Service
@RequiredArgsConstructor
public class ManagementStaffServiceImpl {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public AuthenticationResponse assignNewStaffMember(RegisterRequest request) throws Exception {
        return authenticationService.register(request);
    }
}
