package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.Course;
import com.nsadisha.lms.api.model.Role;
import com.nsadisha.lms.api.model.Teacher;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.repository.TeacherRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

/**
 * @author Sadisha Nimsara
 * @created 07 of Mar 2023
 **/
@ExtendWith(SpringExtension.class)
class TeacherServiceTest {
    @Mock private TeacherRepository teacherRepository;
    @InjectMocks private TeacherService teacherService;

    @BeforeEach
    public void setup() {
        teacherService = spy(teacherService);
    }

    @Test
    public void should_return_a_teacher_by_email() {
        String email = "teacher@test.com";
        User user = User.builder()
                .first_name("Test")
                .last_name("Test")
                .email(email)
                .password("password")
                .role(Role.TEACHER).build();
        Teacher teacher = new Teacher(user);

        when(teacherRepository.findTeacherByEmail(email)).thenReturn(Optional.of(teacher));
        doReturn(teacher).when(teacherService).getTeacher(email);
        Teacher resultTeacher = teacherService.getTeacher(email);


        assertNotNull(resultTeacher);
        assertEquals(email, resultTeacher.getEmail());
    }

    @Test
    public void should_throw_an_exception_when_email_is_not_found() {
        String email = "test@test.com";

        assertThrows(UsernameNotFoundException.class, () -> {
            teacherService.getTeacher(email);
        });
    }

    @Test
    public void should_return_conducting_courses_of_a_teacher() {
        String email = "teacher@test.com";
        User user = User.builder()
                .first_name("Test")
                .last_name("Test")
                .email(email)
                .password("password")
                .role(Role.TEACHER).build();
        Teacher teacher = new Teacher(user);

        when(teacherRepository.findTeacherByEmail(email)).thenReturn(Optional.of(teacher));
        doReturn(teacher).when(teacherService).getTeacher(email);
        when(teacherService.getConductingCourses(email)).thenReturn(anyList());
        List<Course> courses = teacherService.getConductingCourses(email);

        assertNotNull(courses);
    }
}