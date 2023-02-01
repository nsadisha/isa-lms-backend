package com.nsadisha.lms.api.controller;

import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    ResponseEntity<User> saveUser(User user) {
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @GetMapping("/get")
    ResponseEntity<User> getUser(@RequestParam String email) throws UsernameNotFoundException{
        User user = userService.getUser(email);
        if(user == null) {
            throw new UsernameNotFoundException("No user found with "+email);
        }
        return ResponseEntity.ok().body(user);
    }
}
