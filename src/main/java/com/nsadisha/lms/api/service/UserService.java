package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.model.UserFactory;
import com.nsadisha.lms.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserService {
    private final UserFactory userFactory;
    private final UserRepository userRepository;

    public User getUser(String email) throws UsernameNotFoundException{
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with "+email));
        return userFactory.getInstance(user);
    }
}
