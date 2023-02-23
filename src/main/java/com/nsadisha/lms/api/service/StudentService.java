package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.Student;
import com.nsadisha.lms.api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Sadisha Nimsara
 * @created 23 of Feb 2023
 **/
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student getStudent(String email) throws UsernameNotFoundException{
        return studentRepository.findStudentByEmail(email).orElseThrow(
                () -> {throw new UsernameNotFoundException("No student found with "+email);}
        );
    }
}
