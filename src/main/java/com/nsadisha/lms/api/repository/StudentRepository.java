package com.nsadisha.lms.api.repository;

import com.nsadisha.lms.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sadisha Nimsara
 * @created 23 of Feb 2023
 **/
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
