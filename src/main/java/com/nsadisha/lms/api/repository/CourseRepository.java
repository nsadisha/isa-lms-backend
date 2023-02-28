package com.nsadisha.lms.api.repository;

import com.nsadisha.lms.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 21 of Feb 2023
 **/
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findCoursesByNameContaining(String name);
}
