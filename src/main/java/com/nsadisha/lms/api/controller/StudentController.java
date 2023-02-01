package com.nsadisha.lms.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sadisha Nimsara
 * @created 01 of Feb 2023
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/test")
    public String test(){
        return "Hello from student controller";
    }
}
