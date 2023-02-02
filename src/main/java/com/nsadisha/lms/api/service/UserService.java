package com.nsadisha.lms.api.service;

import com.nsadisha.lms.api.model.User;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/
public interface UserService {
    User getUser(String email) throws Exception;
}
