package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.Course;
import com.nsadisha.lms.api.model.Role;
import com.nsadisha.lms.api.model.Student;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.repository.StudentRepository;
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
class StudentServiceTest {
    @Mock private StudentRepository studentRepository;

    @InjectMocks private StudentService studentService;

    @Test
    public void should_return_a_student_by_email(){
        // GIVEN
        String email = "teacher@test.com";
        User user = User.builder()
                .first_name("Test")
                .last_name("Test")
                .email(email)
                .password("password")
                .role(Role.TEACHER).build();
        Student student = new Student(user);

        // WHEN
        when(studentRepository.findStudentByEmail(email)).thenReturn(Optional.of(student));
        Student resultStudent = studentService.getStudent(email);

        // THEN
        assertNotNull(resultStudent);
        assertEquals(email, resultStudent.getEmail());
    }

    @Test
    public void should_throw_an_exception_when_email_is_not_found(){
        // GIVEN
        String email = "teacher@test.com";

        // WHEN
        when(studentRepository.findStudentByEmail(email)).thenThrow(UsernameNotFoundException.class);

        // THEN
        assertThrows(UsernameNotFoundException.class, () -> studentService.getStudent(email));
    }

    @Test
    public void should_return_enrolled_courses_of_a_student(){
        // GIVEN
        String email = "teacher@test.com";
        User user = User.builder()
                .first_name("Test")
                .last_name("Test")
                .email(email)
                .password("password")
                .role(Role.TEACHER).build();
        Student student = new Student(user);

        // WHEN
        when(studentRepository.findStudentByEmail(email)).thenReturn(Optional.of(student));
        List<Course> courses = studentService.getEnrolledCourses(email);

        // THEN
        assertNotNull(courses);
    }
}