package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.Course;
import com.nsadisha.lms.api.model.Student;
import com.nsadisha.lms.api.model.StudentCourseRegistration;
import com.nsadisha.lms.api.repository.StudentCourseRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Sadisha Nimsara
 * @created 23 of Feb 2023
 **/
@Service
@RequiredArgsConstructor
public class StudentCourseRegistrationService {
    private final StudentCourseRegistrationRepository registrationRepository;

    public StudentCourseRegistration save(StudentCourseRegistration registration) {
        return registrationRepository.save(registration);
    }

    public StudentCourseRegistration getRegistration(Course course, Student student) {
        return registrationRepository.findStudentCourseRegistrationByCourseAndStudent(course, student).orElseThrow();
    }

    public void delete(StudentCourseRegistration registration) {
        registrationRepository.delete(registration);
    }
}
