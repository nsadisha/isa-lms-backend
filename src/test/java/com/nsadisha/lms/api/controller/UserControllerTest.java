package com.nsadisha.lms.api.controller;

import com.nsadisha.lms.api.filter.JwtService;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.service.TokenInvalidationService;
import com.nsadisha.lms.api.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author Sadisha Nimsara
 * @created 01 of Mar 2023
 **/

@WebMvcTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JwtService.class, TokenInvalidationService.class, UserController.class})
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    public void should_return_a_user() throws Exception {
        // GIVEN
        when(userService.getUser(any())).thenReturn(any(User.class));

        // WHEN
        ResultActions response = mockMvc.perform(
                get("/user/info")
                        .secure(true)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // THEN
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}