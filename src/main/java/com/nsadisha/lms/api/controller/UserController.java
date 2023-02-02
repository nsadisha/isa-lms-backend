package com.nsadisha.lms.api.controller;

import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/info")
    ResponseEntity<?> getUserInfo(Authentication auth) throws Exception{
        User user = userService.getUser(auth.getName());
        return ResponseEntity.ok(user);
    }
}
