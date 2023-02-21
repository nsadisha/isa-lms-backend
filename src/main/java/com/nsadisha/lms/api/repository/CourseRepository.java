package com.nsadisha.lms.api.repository;

import com.nsadisha.lms.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sadisha Nimsara
 * @created 21 of Feb 2023
 **/
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
