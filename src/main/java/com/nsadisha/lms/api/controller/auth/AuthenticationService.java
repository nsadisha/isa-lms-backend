package com.nsadisha.lms.api.controller.auth;

import com.nsadisha.lms.api.exception.EmailAlreadyInUseException;
import com.nsadisha.lms.api.filter.JwtService;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.model.UserFactory;
import com.nsadisha.lms.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

            userRepository.save(userFactory.getInstance(user));

            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(user)
                    .build();
        }catch(DataIntegrityViolationException e){
            e.printStackTrace();
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
            throw new UsernameNotFoundException("Email or password is incorrect.");
        }catch(Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }
}
