package com.nsadisha.lms.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from MainController");
    }
}
