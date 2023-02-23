package com.nsadisha.lms.api.repository;

import com.nsadisha.lms.api.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Sadisha Nimsara
 * @created 23 of Feb 2023
 **/
@Repository @PreAuthorize("hasAuthority('TEACHER')")
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findTeacherByEmail(String email);
}
