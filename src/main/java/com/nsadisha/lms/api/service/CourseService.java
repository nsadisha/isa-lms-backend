package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.exception.CourseCreationFailureException;
import com.nsadisha.lms.api.exception.CourseNotFoundException;
import com.nsadisha.lms.api.model.Course;
import com.nsadisha.lms.api.model.Student;
import com.nsadisha.lms.api.model.StudentCourseRegistration;
import com.nsadisha.lms.api.model.Teacher;
import com.nsadisha.lms.api.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 22 of Feb 2023
 **/
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class CourseService {
//    private final StudentCourseRegistrationRepository registrationRepository;
    private final StudentCourseRegistrationService registrationService;
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public Course createNewCourse(Course course, String email) throws Exception {
        try {
            Teacher conductor = teacherService.getTeacher(email);
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

    public List<Course> search(String query) {
        return courseRepository.findCoursesByNameContaining(query);
    }

    public StudentCourseRegistration enrollStudent(int courseId, String email) throws Exception {
        try{
            Student student = studentService.getStudent(email);
            Course course = getCourseById(courseId);

            StudentCourseRegistration registration = StudentCourseRegistration
                    .builder()
                    .student(student)
                    .course(course)
                    .registrationDate(LocalDateTime.now())
                    .build();

            student.getRegistrations().add(registration);
            course.getRegistrations().add(registration);

            return registrationService.save(registration);
        }catch(DataIntegrityViolationException e) {
            throw new CourseCreationFailureException("You have already enrolled in this course.");
        }catch(Exception e) {
            throw new CourseCreationFailureException(e.getLocalizedMessage());
        }
    }

    public void unenrollStudent(int courseId, String email) throws Exception{
        Student student = studentService.getStudent(email);
        Course course = getCourseById(courseId);
        StudentCourseRegistration registration = registrationService.getRegistration(course, student);

        registrationService.delete(registration);
        student.getRegistrations().remove(registration);
        course.getRegistrations().remove(registration);
    }

    public List<Student> getEnrolledStudents(int courseId) throws Exception {
        Course course = getCourseById(courseId);
        return course.getRegistrations().stream().map(StudentCourseRegistration::getStudent).toList();
    }
}
