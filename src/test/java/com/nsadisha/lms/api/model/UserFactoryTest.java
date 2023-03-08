package com.nsadisha.lms.api.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sadisha Nimsara
 * @created 07 of Mar 2023
 **/
class UserFactoryTest {
    private UserFactory userFactory;

    @BeforeEach
    public void setup() {
        userFactory = new UserFactory();
    }

    @Test
    public void should_return_an_object_of_student_when_role_is_student() {
        // GIVEN
        User user = User.builder()
                .first_name("Test")
                .last_name("Test")
                .email("test@email.com")
                .password("password")
                .role(Role.STUDENT).build();

        // WHEN
        User resultUser = userFactory.getInstance(user);

        // THEN
        assertNotNull(resultUser);
        assertEquals(user.getEmail(), resultUser.getEmail());
        assertTrue(resultUser instanceof Student);
    }

    @Test
    public void should_return_an_object_of_teacher_when_role_is_teacher() {
        // GIVEN
        User user = User.builder()
                .first_name("Test")
                .last_name("Test")
                .email("test@email.com")
                .password("password")
                .role(Role.TEACHER).build();

        // WHEN
        User resultUser = userFactory.getInstance(user);

        // THEN
        assertNotNull(resultUser);
        assertEquals(user.getEmail(), resultUser.getEmail());
        assertTrue(resultUser instanceof Teacher);
    }

    @Test
    public void should_return_an_object_of_management_staff_when_role_is_management_staff() {
        // GIVEN
        User user = User.builder()
                .first_name("Test")
                .last_name("Test")
                .email("test@email.com")
                .password("password")
                .role(Role.MANAGEMENT_STAFF).build();

        // WHEN
        User resultUser = userFactory.getInstance(user);

        // THEN
        assertNotNull(resultUser);
        assertEquals(user.getEmail(), resultUser.getEmail());
        assertTrue(resultUser instanceof ManagementStaff);
    }
}