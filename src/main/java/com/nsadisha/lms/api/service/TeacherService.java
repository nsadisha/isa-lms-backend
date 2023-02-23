package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.Teacher;
import com.nsadisha.lms.api.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Sadisha Nimsara
 * @created 23 of Feb 2023
 **/
@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public Teacher getTeacher(String email) throws UsernameNotFoundException{
        return teacherRepository.findTeacherByEmail(email).orElseThrow(
                () -> {throw new UsernameNotFoundException("No teacher found with "+email);
                }
        );
    }
}
