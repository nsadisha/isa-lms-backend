package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.exception.CourseNotFoundException;
import com.nsadisha.lms.api.model.Course;
import com.nsadisha.lms.api.model.Role;
import com.nsadisha.lms.api.model.Teacher;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Sadisha Nimsara
 * @created 07 of Mar 2023
 **/
@ExtendWith(SpringExtension.class)
class CourseServiceTest {
    @Mock private StudentCourseRegistrationService registrationService;
    @Mock private TeacherService teacherService;
    @Mock private StudentService studentService;
    @Mock private CourseRepository courseRepository;

    @InjectMocks private CourseService courseService;

    @Test
    public void should_return_a_list_of_courses() {
        // GIVEN
        List<Course> courses = List.of();

        // WHEN
        when(courseRepository.findAll()).thenReturn(courses);
        List<Course> responseCourses = courseService.getAllCourses();

        // THEN
        assertNotNull(responseCourses);
    }

    @Test
    public void should_return_a_list_of_courses_on_search() {
        // GIVEN
        List<Course> searchedCourses = List.of();

        // WHEN
        when(courseRepository.findCoursesByNameContainingIgnoreCase(any())).thenReturn(searchedCourses);
        List<Course> searchResults = courseService.search(any());

        // THEN
        assertNotNull(searchResults);
    }

    @Test
    public void should_return_a_course_with_a_given_id() throws Exception {
        // GIVEN
        Course course = Course.builder()
                .id(0)
                .courseCode("ABCD 12345")
                .name("Test course")
                .description("Description")
                .build();

        // WHEN
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        Course resultCourse = courseService.getCourseById(course.getId());

        // THEN
        assertNotNull(resultCourse);
        assertEquals(course.getId(), resultCourse.getId());
        assertEquals(course.getCourseCode(), resultCourse.getCourseCode());
    }

    @Test
    public void should_throw_an_exception_when_course_is_not_found() {
        assertThrows(CourseNotFoundException.class, () -> courseService.getCourseById(-1));
    }

    @Test
    public void should_create_a_new_course() throws Exception {
        // GIVEN
        Course course = Course.builder()
                .id(0)
                .name("Test course")
                .courseCode("ABCD 12345")
                .description("Test description")
                .build();

        User user = User.builder()
                .first_name("Test")
                .last_name("Test")
                .email("test@email.com")
                .password("password")
                .role(Role.TEACHER).build();

        Teacher teacher = new Teacher(user);

        // WHEN
        when(teacherService.getTeacher(teacher.getEmail())).thenReturn(teacher);
        when(courseService.createNewCourse(course, teacher.getEmail())).thenReturn(course);
        Course resultCourse = courseService.createNewCourse(course, teacher.getEmail());

        assertNotNull(resultCourse);
        assertEquals(course.getCourseCode(), resultCourse.getCourseCode());
        assertEquals(teacher, resultCourse.getConductor());
    }

    @Test
    public void should_throw_an_exception_when_creating_courses_with_duplicate_course_codes() {
        // TODO : complete
    }
}