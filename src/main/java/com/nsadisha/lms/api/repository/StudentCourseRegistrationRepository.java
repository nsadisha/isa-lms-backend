package com.nsadisha.lms.api.repository;

import com.nsadisha.lms.api.model.StudentCourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sadisha Nimsara
 * @created 22 of Feb 2023
 **/
@Repository
public interface StudentCourseRegistrationRepository extends JpaRepository<StudentCourseRegistration, Integer> {
}
