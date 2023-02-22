package com.nsadisha.lms.api.sample;

import com.nsadisha.lms.api.controller.auth.AuthenticationService;
import com.nsadisha.lms.api.controller.auth.RegisterRequest;
import com.nsadisha.lms.api.model.Course;
import com.nsadisha.lms.api.model.Role;
import com.nsadisha.lms.api.service.CourseService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/
@Component
@RequiredArgsConstructor
public class SampleData {
    private final AuthenticationService authenticationService;
    private final CourseService courseService;

    @PostConstruct
    private void addSampleUsers() throws Exception {
        authenticationService.register(
                new RegisterRequest(
                        "Sadisha",
                        "Nimsara",
                        "nsadisha@gmail.com",
                        "123123",
                        Role.STUDENT
                )
        );
        authenticationService.register(
                new RegisterRequest(
                        "Malshani",
                        "Dahanayake",
                        "malshani@gmail.com",
                        "123123",
                        Role.TEACHER
                )
        );
        authenticationService.register(
                new RegisterRequest(
                        "Pubudu",
                        "Wickramathunga",
                        "pubudu@gmail.com",
                        "123123",
                        Role.MANAGEMENT_STAFF
                )
        );
    }

    @PostConstruct
    private void addSampleCourses() throws Exception {
        Course course1 = Course.builder()
                .courseCode("SENG 12345")
                .name("Test Course 1")
                .description("Test Description")
                .build();
        courseService.createNewCourse(course1, "malshani@gmail.com");

        Course course2 = Course.builder()
                .courseCode("SENG 12354")
                .name("Test Course 2")
                .description("Test Description")
                .build();
        courseService.createNewCourse(course2, "malshani@gmail.com");

        Course course3 = Course.builder()
                .courseCode("SENG 12343")
                .name("Test Course 3")
                .description("Test Description")
                .build();
        courseService.createNewCourse(course3, "malshani@gmail.com");
    }

    @PostConstruct
    private void enrollStudentsToCourses() throws Exception {
        courseService.enrollStudent(1, "nsadisha@gmail.com");
        courseService.enrollStudent(3, "nsadisha@gmail.com");
    }
}
