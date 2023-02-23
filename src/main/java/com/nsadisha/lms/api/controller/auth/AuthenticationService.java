package com.nsadisha.lms.api.controller.auth;

import com.nsadisha.lms.api.exception.EmailAlreadyInUseException;
import com.nsadisha.lms.api.filter.JwtService;
import com.nsadisha.lms.api.model.*;
import com.nsadisha.lms.api.repository.ManagementStaffRepository;
import com.nsadisha.lms.api.repository.StudentRepository;
import com.nsadisha.lms.api.repository.TeacherRepository;
import com.nsadisha.lms.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Sadisha Nimsara
 * @created 01 of Feb 2023
 **/
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final ManagementStaffRepository managementRepository;

    private final UserFactory userFactory;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) throws Exception{
        try{
            request.validateRegisterRequest();

            User user = User.builder()
                    .first_name(request.getFirst_name())
                    .last_name(request.getLast_name())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();

            switch(request.getRole()) {
                case STUDENT -> studentRepository.save((Student) userFactory.getInstance(user));
                case TEACHER -> teacherRepository.save((Teacher) userFactory.getInstance(user));
                case MANAGEMENT_STAFF -> managementRepository.save((ManagementStaff) userFactory.getInstance(user));
            }

            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(user)
                    .build();
        }catch(DataIntegrityViolationException e){
            throw new EmailAlreadyInUseException("Your email address is already in use.");
        }catch(Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();

            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(user)
                    .build();
        }catch(BadCredentialsException e) {
            throw new BadCredentialsException("Email or password is incorrect.");
        }catch(Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public String logout(String token) {
        boolean logoutStatus = jwtService.blacklistToken(token);
        return logoutStatus ? "Logout success" : "Logout failed";
    }
}
