package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 08 of Feb 2023
 **/
@Service
@RequiredArgsConstructor
@Transactional
public class ManagementStaffServiceImpl {
    private final UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
