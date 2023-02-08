package com.nsadisha.lms.api.controller;

import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.service.ManagementStaffServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 08 of Feb 2023
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/management")
public class ManagementStaffController {
    private final ManagementStaffServiceImpl managementStaffService;
    @GetMapping("/get_all_users")
    public ResponseEntity<List<User>> test() {
        return ResponseEntity.ok(managementStaffService.getAllUsers());
    }
}
