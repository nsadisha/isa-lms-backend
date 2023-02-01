package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.model.UserFactory;
import com.nsadisha.lms.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/
@Service
@RequiredArgsConstructor
@Transactional @Slf4j
public class UserServiceImpl implements UserService{
    private final UserFactory userFactory;
    private final UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(
                userFactory.getInstance(user)
        );
    }

    @Override
    public User getUser(String email) {
        return userFactory.getInstance(
                userRepository.findUserByEmail(email)
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
