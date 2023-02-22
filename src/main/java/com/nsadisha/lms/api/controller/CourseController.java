package com.nsadisha.lms.api.controller;

import com.nsadisha.lms.api.model.Course;
import com.nsadisha.lms.api.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 22 of Feb 2023
 **/
@RestController
@AllArgsConstructor
@RequestMapping("course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id) throws Exception {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Course> createNewCourse(
            @RequestBody Course course,
            Authentication authentication
    ) throws Exception {
        return ResponseEntity.ok(courseService.createNewCourse(course, authentication.getName()));
    }
}
