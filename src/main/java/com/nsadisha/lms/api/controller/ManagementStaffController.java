package com.nsadisha.lms.api.controller;

import com.nsadisha.lms.api.controller.auth.RegisterRequest;
import com.nsadisha.lms.api.model.User;
import com.nsadisha.lms.api.service.ManagementStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 08 of Feb 2023
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/management")
public class ManagementStaffController {
    private final ManagementStaffService managementStaffService;

    @GetMapping("/get_all_users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(managementStaffService.getAllUsers());
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignNewStaffMember(@RequestBody RegisterRequest registerRequest) throws Exception {
        return ResponseEntity.ok(managementStaffService.assignNewStaffMember(registerRequest));
    }
}
