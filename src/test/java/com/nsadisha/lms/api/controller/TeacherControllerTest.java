package com.nsadisha.lms.api.controller;

import com.nsadisha.lms.api.repository.CourseRepository;
import com.nsadisha.lms.api.repository.StudentCourseRegistrationRepository;
import com.nsadisha.lms.api.repository.StudentRepository;
import com.nsadisha.lms.api.repository.TeacherRepository;
import com.nsadisha.lms.api.service.CourseService;
import com.nsadisha.lms.api.service.StudentCourseRegistrationService;
import com.nsadisha.lms.api.service.StudentService;
import com.nsadisha.lms.api.service.TeacherService;
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

import static org.mockito.ArgumentMatchers.anyString;
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
        CourseService.class,
        StudentService.class,
        StudentCourseRegistrationService.class,
        TeacherController.class
})
@Disabled
class TeacherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean private StudentCourseRegistrationRepository registrationRepository;
    @MockBean private CourseRepository courseRepository;
    @MockBean private StudentRepository studentRepository;
    @MockBean private TeacherRepository teacherRepository;

    @MockBean
    private TeacherService teacherService;

    @Test
    @WithMockUser
    public void should_return_a_list_of_conducting_courses() throws Exception {
        // GIVEN
        when(teacherService.getConductingCourses(anyString())).thenReturn(
                List.of()
        );

        // WHEN
        ResultActions response = mockMvc.perform(
                get("/teacher/courses")
                        .secure(true)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // THEN
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}