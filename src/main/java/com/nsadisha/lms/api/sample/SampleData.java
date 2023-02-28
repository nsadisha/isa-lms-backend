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
        final String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vitae ante convallis nunc aliquam mattis. Etiam non nisl id elit auctor porta. Vestibulum scelerisque diam at mauris pretium, non aliquam ligula pharetra. Proin pharetra enim eget rhoncus euismod. Suspendisse lacinia eleifend tempus. Pellentesque vulputate efficitur hendrerit. Nam hendrerit lacus diam, vel finibus diam hendrerit vel. Aliquam commodo tortor massa, nec rutrum tortor tristique sed. Phasellus ullamcorper volutpat turpis sit amet venenatis. Aenean sed aliquet quam, ut porttitor augue. Fusce egestas auctor tortor sit amet placerat. Suspendisse potenti. Donec sit amet est metus. Proin consectetur nisi volutpat rutrum viverra. Maecenas facilisis eros non libero volutpat ullamcorper. Aliquam posuere felis in metus rhoncus, eget scelerisque mauris ultrices. ";
        Course course1 = Course.builder()
                .courseCode("SENG 11343")
                .name("Programming Concepts")
                .description(description)
                .build();
        courseService.createNewCourse(course1, "malshani@gmail.com");

        Course course2 = Course.builder()
                .courseCode("SENG 22354")
                .name("Computer Architecture and Operating Systems")
                .description(description)
                .build();
        courseService.createNewCourse(course2, "malshani@gmail.com");

        Course course3 = Course.builder()
                .courseCode("SENG 12343")
                .name("Data Structures and Algorithms")
                .description(description)
                .build();
        courseService.createNewCourse(course3, "malshani@gmail.com");

        Course course4 = Course.builder()
                .courseCode("SENG 31342")
                .name("Information Security")
                .description(description)
                .build();
        courseService.createNewCourse(course4, "malshani@gmail.com");

        Course course5 = Course.builder()
                .courseCode("SENG 22343")
                .name("Database Design")
                .description(description)
                .build();
        courseService.createNewCourse(course5, "malshani@gmail.com");
    }

    @PostConstruct
    private void enrollStudentsToCourses() throws Exception {
        courseService.enrollStudent(1, "nsadisha@gmail.com");
        courseService.enrollStudent(3, "nsadisha@gmail.com");
    }
}
