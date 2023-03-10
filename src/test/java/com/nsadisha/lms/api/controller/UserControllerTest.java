package com.nsadisha.lms.api.controller;

import com.nsadisha.lms.api.model.Role;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Sadisha Nimsara
 * @created 08 of Mar 2023
 **/
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Disabled
class UserControllerTest {
    @Mock private UserService userService;
    @InjectMocks private UserController userController;

    @Test
    @WithMockUser
    public void should_return_a_user() {
        User user = User.builder()
                .first_name("Test")
                .last_name("User")
                .email("test@gmail.com")
                .role(Role.STUDENT)
                .password("password").build();

        when(userService.getUser(any())).thenReturn(user);
        User resultUser = userController.getUserInfo(any()).getBody();

        assertNotNull(resultUser);
        System.out.println(resultUser.getEmail());
    }
}