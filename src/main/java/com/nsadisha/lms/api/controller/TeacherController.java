package com.nsadisha.lms.api.controller;

import com.nsadisha.lms.api.model.Course;
import com.nsadisha.lms.api.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 23 of Feb 2023
 **/
@RestController
@AllArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getConducting(Authentication authentication) {
        return ResponseEntity.ok(teacherService.getConductingCourses(authentication.getName()));
    }
}
