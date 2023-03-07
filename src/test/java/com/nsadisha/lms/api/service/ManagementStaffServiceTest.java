package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.controller.auth.AuthenticationResponse;
import com.nsadisha.lms.api.controller.auth.AuthenticationService;
import com.nsadisha.lms.api.controller.auth.RegisterRequest;
import com.nsadisha.lms.api.model.Role;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @author Sadisha Nimsara
 * @created 07 of Mar 2023
 **/
@ExtendWith(SpringExtension.class)
class ManagementStaffServiceTest {
    @Mock private UserRepository userRepository;
    @Mock private AuthenticationService authenticationService;

    @InjectMocks private ManagementStaffService managementStaffService;

    @Test
    public void should_return_a_list_of_all_users() {
        List<User> users = List.of();

        when(managementStaffService.getAllUsers()).thenReturn(users);
        List<User> allUsers = managementStaffService.getAllUsers();

        assertNotNull(allUsers);
    }

    @Test
    public void should_assign_a_new_staff_member() throws Exception {
        // GIVEN
        RegisterRequest request = RegisterRequest.builder()
                .first_name("Test")
                .last_name("Staff")
                .email("test@test.com")
                .password("password")
                .build();

        User user = User.builder()
                .first_name(request.getFirst_name())
                .last_name(request.getLast_name())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.MANAGEMENT_STAFF)
                .build();

        // WHEN
        when(authenticationService.register(request)).thenReturn(
                AuthenticationResponse.builder()
                        .user(user)
                        .build()
        );
        AuthenticationResponse response = managementStaffService.assignNewStaffMember(request);
        User responseUser = (User) response.getUser();

        // THEN
        assertNotNull(response);
        assertEquals(Role.MANAGEMENT_STAFF, responseUser.getRole());
        assertEquals(request.getEmail(), responseUser.getEmail());
    }
}