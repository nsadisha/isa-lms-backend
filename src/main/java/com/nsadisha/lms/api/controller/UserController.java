package com.nsadisha.lms.api.controller;

import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    ResponseEntity<User> saveUser(User user) throws Exception{
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @GetMapping("/get")
    ResponseEntity<User> getUser(@RequestParam String email) throws Exception{
        User user = userService.getUser(email);
        return ResponseEntity.ok().body(user);
    }
}
