package com.nsadisha.lms.api.controller;

import com.nsadisha.lms.api.controller.auth.AuthenticationResponse;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.service.ManagementStaffService;
import org.junit.jupiter.api.Disabled;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author Sadisha Nimsara
 * @created 03 of Mar 2023
 **/
@WebMvcTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ManagementStaffController.class
})
@Disabled
class ManagementStaffControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManagementStaffService managementStaffService;

    @Test
    @WithMockUser(username = "hello@gmail.com", password = "password", authorities = {"MANAGEMENT_STAFF"})
    public void should_return_all_users() throws Exception {
        // GIVEN
        when(managementStaffService.getAllUsers()).thenReturn(List.of());

        // WHEN
        ResultActions response = mockMvc.perform(
                get("/management/get_all_users")
                        .secure(true)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // THEN
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "hello@gmail.com", password = "password", authorities = {"MANAGEMENT_STAFF"})
    public void should_assign_a_new_staff_member() throws Exception {
        // GIVEN
        when(managementStaffService.assignNewStaffMember(any()))
                .thenReturn(any(AuthenticationResponse.class));

        // WHEN
        ResultActions response = mockMvc.perform(
                get("/management/assign")
                        .secure(true)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // THEN
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}