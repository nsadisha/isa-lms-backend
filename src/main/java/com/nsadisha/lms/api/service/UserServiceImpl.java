package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.rmi.server.LogStream.log;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/
@Service
@RequiredArgsConstructor
@Transactional @Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        log("Log all users");
        return userRepository.findAll();
    }
}
