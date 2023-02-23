package com.nsadisha.lms.api.controller;

import com.nsadisha.lms.api.model.Course;
import com.nsadisha.lms.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 01 of Feb 2023
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getEnrolledCourses(Authentication authentication){
        return ResponseEntity.ok(studentService.getEnrolledCourses(authentication.getName()));
    }
}
