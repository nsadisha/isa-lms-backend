package com.nsadisha.lms.api.controller;

import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    User saveUser(User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/get")
    User getUser(@RequestParam String email) {
        return userService.getUser(email);
    }

    @GetMapping("/all")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
