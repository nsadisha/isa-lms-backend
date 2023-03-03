package com.nsadisha.lms.api.controller.auth;

import com.nsadisha.lms.api.exception.EmailAlreadyInUseException;
import com.nsadisha.lms.api.model.Role;
import com.nsadisha.lms.api.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Sadisha Nimsara
 * @created 01 of Mar 2023
 **/
@SpringBootTest
@ExtendWith(SpringExtension.class)
class AuthenticationServiceTest {
    @MockBean
    private UserRepository userRepository;
    @Mock
    private AuthenticationService authenticationService;

    @AfterEach
    void setUpAfter() {
        userRepository.deleteAll();
    }

    @Test
    public void test() {

    }

//    @Test @Order(1)
//    public void should_register_a_user() throws Exception{
//        RegisterRequest request = RegisterRequest.builder()
//                .first_name("Test")
//                .last_name("User")
//                .email("test@gmail.com")
//                .password("password")
//                .role(Role.STUDENT)
//                .build();
//
//        AuthenticationResponse response = authenticationService.register(request);
//
//        assertEquals(request.getEmail(), response.getUser().getUsername());
//    }

//    @Test @Order(2)
//    public void should_not_register_a_user_with_duplicate_email() {
//        RegisterRequest request1 = RegisterRequest.builder()
//                .first_name("Test")
//                .last_name("User")
//                .email("test@gmail.com")
//                .password("password")
//                .role(Role.STUDENT)
//                .build();
//
//        RegisterRequest request2 = RegisterRequest.builder()
//                .first_name("Test")
//                .last_name("User 2")
//                .email("test@gmail.com")
//                .password("password")
//                .role(Role.STUDENT)
//                .build();
//
//
//        Exception exception = assertThrows(EmailAlreadyInUseException.class, () -> {
//            authenticationService.register(request1);
//            authenticationService.register(request2);
//        });
//
//        String expectedMessage = "Your email address is already in use.";
//
//        assertEquals(expectedMessage, exception.getLocalizedMessage());
//    }
}