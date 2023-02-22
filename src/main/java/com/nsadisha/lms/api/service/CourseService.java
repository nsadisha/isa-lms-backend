package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.exception.CourseCreationFailureException;
import com.nsadisha.lms.api.exception.CourseNotFoundException;
import com.nsadisha.lms.api.model.Course;
import com.nsadisha.lms.api.model.Teacher;
import com.nsadisha.lms.api.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 22 of Feb 2023
 **/
@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserServiceImpl userService;

    public Course createNewCourse(Course course, Authentication authentication) throws Exception {
        try {
            Teacher conductor = (Teacher) userService.getUser(authentication.getName());
            course.setConductor(conductor);
            conductor.getConductingCourses().add(course);
            return courseRepository.save(course);
        } catch(DataIntegrityViolationException e) {
            throw new CourseCreationFailureException("Cannot create a new course with already existing course code.");
        } catch(Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id) throws Exception {
        return courseRepository.findById(id).orElseThrow(
                () -> new CourseNotFoundException("Cannot find a course with id: "+id)
        );
    }
}
