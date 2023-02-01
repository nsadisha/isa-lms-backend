package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.User;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/
public interface UserService {
    User saveUser(User user) throws Exception;
    User getUser(String email) throws Exception;
    List<User> getAllUsers();
}
