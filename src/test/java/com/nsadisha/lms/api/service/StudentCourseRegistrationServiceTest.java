package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.*;
import com.nsadisha.lms.api.repository.StudentCourseRegistrationRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Sadisha Nimsara
 * @created 07 of Mar 2023
 **/
@ExtendWith(SpringExtension.class)
class StudentCourseRegistrationServiceTest {
    @Mock private StudentCourseRegistrationRepository registrationRepository;

    @InjectMocks private StudentCourseRegistrationService registrationService;

    @Test
    public void should_save_a_course_registration() {
        // GIVEN
        User user = User.builder()
                .first_name("Test")
                .last_name("Test")
                .email("test@gmail.com")
                .password("password")
                .role(Role.TEACHER).build();
        Student student = new Student(user);

        Course course = Course.builder()
                .id(0)
                .name("Test Course")
                .description("Test description")
                .courseCode("ABCD 12345").build();

        StudentCourseRegistration registration = StudentCourseRegistration.builder()
                .id(0)
                .student(student)
                .course(course)
                .registrationDate(LocalDateTime.now())
                .build();

        // WHEN
        when(registrationRepository.save(registration)).thenReturn(registration);
        StudentCourseRegistration resultRegistration = registrationService.save(registration);

        // THEN
        assertEquals(registration.getId(), resultRegistration.getId());
        assertEquals(registration.getStudent(), resultRegistration.getStudent());
        assertEquals(registration.getCourse(), resultRegistration.getCourse());
    }

    @Test
    public void should_return_an_existing_registration() {
        // GIVEN
        User user = User.builder()
                .first_name("Test")
                .last_name("Test")
                .email("test@gmail.com")
                .password("password")
                .role(Role.TEACHER).build();
        Student student = new Student(user);

        Course course = Course.builder()
                .id(0)
                .name("Test Course")
                .description("Test description")
                .courseCode("ABCD 12345").build();

        // WHEN
        when(registrationRepository.findStudentCourseRegistrationByCourseAndStudent(
                course,
                student
        )).thenReturn(Optional.ofNullable(
                StudentCourseRegistration.builder()
                        .student(student)
                        .course(course)
                        .build())
        );

        StudentCourseRegistration registration = registrationService.getRegistration(course, student);

        // THEN
        assertNotNull(registration);
        assertEquals(course, registration.getCourse());
        assertEquals(student, registration.getStudent());
    }

    @Test @Disabled
    public void should_delete_a_registration() {
        // TODO : test case is not working
        // GIVEN
        StudentCourseRegistration registration = StudentCourseRegistration.builder()
                .id(anyInt())
                .student(any(Student.class))
                .course(any(Course.class))
                .build();

        // WHEN
        doNothing().when(registrationRepository).delete(any(StudentCourseRegistration.class));
        doNothing().when(registrationService).delete(any(StudentCourseRegistration.class));

        // THEN
        verify(registrationService, times(1)).delete(registration);
    }
}