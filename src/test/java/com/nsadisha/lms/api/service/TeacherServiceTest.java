package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.Course;
import com.nsadisha.lms.api.model.Role;
import com.nsadisha.lms.api.model.Teacher;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Sadisha Nimsara
 * @created 07 of Mar 2023
 **/
@ExtendWith(SpringExtension.class)
class TeacherServiceTest {
    @Mock private TeacherRepository teacherRepository;
    @InjectMocks private TeacherService teacherService;

    @Test
    public void should_return_a_teacher_by_email() {
        // GIVEN
        String email = "teacher@test.com";
        User user = User.builder()
                .first_name("Test")
                .last_name("Test")
                .email(email)
                .password("password")
                .role(Role.TEACHER).build();
        Teacher teacher = new Teacher(user);

        // WHEN
        when(teacherRepository.findTeacherByEmail(email)).thenReturn(Optional.of(teacher));
        Teacher resultTeacher = teacherService.getTeacher(email);

        // THEN
        assertNotNull(resultTeacher);
        assertEquals(email, resultTeacher.getEmail());
    }

    @Test
    public void should_throw_an_exception_when_email_is_not_found() {
        String email = "test@test.com";

        assertThrows(UsernameNotFoundException.class, () -> teacherService.getTeacher(email));
    }

    @Test
    public void should_return_conducting_courses_of_a_teacher() {
        // GIVEN
        String email = "teacher@test.com";
        User user = User.builder()
                .first_name("Test")
                .last_name("Test")
                .email(email)
                .password("password")
                .role(Role.TEACHER).build();
        Teacher teacher = new Teacher(user);

        // WHEN
        when(teacherRepository.findTeacherByEmail(email)).thenReturn(Optional.of(teacher));
        List<Course> courses = teacherService.getConductingCourses(email);

        // THEN
        assertNotNull(courses);
    }
}