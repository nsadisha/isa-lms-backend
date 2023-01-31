package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.User;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/
public interface UserService {
    User saveUser(User user);
    User getUser(String email);
    List<User> getAllUsers();
}
