package com.nsadisha.lms.api.controller.auth;

import com.nsadisha.lms.api.exception.EmailAlreadyInUseException;
import com.nsadisha.lms.api.exception.InvalidRequestValueException;
import com.nsadisha.lms.api.filter.JwtService;
import com.nsadisha.lms.api.model.Role;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.model.UserFactory;
import com.nsadisha.lms.api.repository.ManagementStaffRepository;
import com.nsadisha.lms.api.repository.StudentRepository;
import com.nsadisha.lms.api.repository.TeacherRepository;
import com.nsadisha.lms.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Sadisha Nimsara
 * @created 07 of Mar 2023
 **/
@ExtendWith(SpringExtension.class)
class AuthenticationServiceTest {
    @Mock private UserRepository userRepository;
    @Mock private StudentRepository studentRepository;
    @Mock private TeacherRepository teacherRepository;
    @Mock private ManagementStaffRepository managementStaffRepository;
    @Mock private UserFactory userFactory;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private JwtService jwtService;
    @Mock private AuthenticationManager authenticationManager;

    @InjectMocks private AuthenticationService authenticationService;

    // Registration test
    @Test
    public void should_register_a_student() throws Exception {
        String email = "test@gmail.com";
        RegisterRequest request = RegisterRequest.builder()
                .first_name("Test")
                .last_name("Student")
                .email(email)
                .password("password")
                .role(Role.STUDENT).build();

        when(authenticationService.register(request)).thenReturn(any());
        AuthenticationResponse response = authenticationService.register(request);
        User responseUser = (User) response.getUser();

        assertNotNull(response);
        assertEquals(request.getRole(), responseUser.getRole());
        assertEquals(email, responseUser.getEmail());
    }

    @Test
    public void should_register_a_teacher() throws Exception {
        String email = "test@gmail.com";
        RegisterRequest request = RegisterRequest.builder()
                .first_name("Test")
                .last_name("Teacher")
                .email(email)
                .password("password")
                .role(Role.TEACHER).build();

        when(authenticationService.register(request)).thenReturn(any());
        AuthenticationResponse response = authenticationService.register(request);
        User responseUser = (User) response.getUser();

        assertNotNull(response);
        assertEquals(request.getRole(), responseUser.getRole());
        assertEquals(email, responseUser.getEmail());
    }

    @Test
    public void should_register_a_management_staff_member() throws Exception {
        String email = "test@gmail.com";
        RegisterRequest request = RegisterRequest.builder()
                .first_name("Test")
                .last_name("Staff")
                .email(email)
                .password("password")
                .role(Role.MANAGEMENT_STAFF).build();

        when(authenticationService.register(request)).thenReturn(any());
        AuthenticationResponse response = authenticationService.register(request);
        User responseUser = (User) response.getUser();

        assertNotNull(response);
        assertEquals(request.getRole(), responseUser.getRole());
        assertEquals(email, responseUser.getEmail());
    }

    @Test
    public void should_not_register_a_user_with_duplicate_email() throws Exception {
        String email = "test@gmail.com";
        RegisterRequest request = RegisterRequest.builder()
                .first_name("Test")
                .last_name("Staff")
                .email(email)
                .password("password")
                .role(Role.STUDENT).build();

        when(authenticationService.register(request)).thenThrow(
                new DataIntegrityViolationException("")
        );

        assertThrows(EmailAlreadyInUseException.class, () -> {
            authenticationService.register(request);
        });
    }

    @Test
    public void should_not_register_a_user_with_empty_fields() {
        String email = "test@gmail.com";
        RegisterRequest request = RegisterRequest.builder()
                .first_name("")
                .last_name("")
                .email(email)
                .password("")
                .role(Role.STUDENT).build();

        assertThrows(InvalidRequestValueException.class, () -> {
            authenticationService.register(request);
        });
    }

    // Authentication tests
    @Test
    public void should_authenticate_a_registered_user() throws Exception {
        String email = "test@email.com";
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email(email)
                .password("password").build();

        when(userRepository.findUserByEmail(email)).thenReturn(
                Optional.of(User.builder()
                        .email(email)
                        .build()
                )
        );
        when(authenticationService.authenticate(request)).thenReturn(any());
        AuthenticationResponse response = authenticationService.authenticate(request);
        User resultUser = (User) response.getUser();

        assertNotNull(response);
        assertEquals(email, resultUser.getEmail());
    }

    @Test
    public void should_not_authenticate_an_unregistered_user() {
        String email = "test@email.com";
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email(email)
                .password("password").build();

        when(userRepository.findUserByEmail(email)).thenThrow(BadCredentialsException.class);
        assertThrows(BadCredentialsException.class, () -> {
            authenticationService.authenticate(request);
        });
    }


}