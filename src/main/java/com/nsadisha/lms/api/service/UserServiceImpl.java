package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.exception.NullUserException;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.model.UserFactory;
import com.nsadisha.lms.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User saveUser(User user) throws Exception {
        if(user.getRole() == null) throw new NullUserException("This user is null");
        return userRepository.save(
                userFactory.getInstance(user)
        );
    }

    @Override
    public User getUser(String email) throws UsernameNotFoundException{
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with "+email));
        return userFactory.getInstance(user);
    }
}
