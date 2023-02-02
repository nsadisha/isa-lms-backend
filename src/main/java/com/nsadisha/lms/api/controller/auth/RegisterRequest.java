package com.nsadisha.lms.api.controller.auth;

import com.nsadisha.lms.api.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sadisha Nimsara
 * @created 01 of Feb 2023
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String first_name;
    private String Last_name;
    private String email;
    private String password;
    private Role role;
}
