package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.Role;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.model.UserFactory;
import com.nsadisha.lms.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Sadisha Nimsara
 * @created 03 of Mar 2023
 **/
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @Mock private UserFactory userFactory;
    @Mock private UserRepository userRepository;

    @InjectMocks private UserService userService;

    @Test
    public void should_return_a_user_by_email(){
        String email = "test@test.com";
        User user = User.builder()
                .first_name("Test")
                .last_name("Test")
                .email(email)
                .password("password")
                .role(Role.STUDENT).build();

        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
        when(userService.getUser(email)).thenReturn(user);
        User resultUser = userService.getUser(email);

        assertNotNull(resultUser);
        assertEquals(email, resultUser.getEmail());
    }

}