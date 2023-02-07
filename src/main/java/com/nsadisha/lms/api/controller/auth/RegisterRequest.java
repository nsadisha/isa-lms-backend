package com.nsadisha.lms.api.controller.auth;

import com.nsadisha.lms.api.exception.InvalidRequestValueException;
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
    private String last_name;
    private String email;
    private String password;
    private Role role;

    public void validateRegisterRequest() throws Exception {
        if (isInvalid(first_name)) {
            throw new InvalidRequestValueException("First name is null or empty.");
        } else if (isInvalid(last_name)) {
            throw new InvalidRequestValueException("Last name is null or empty.");
        } else if (isInvalid(email)) {
            throw new InvalidRequestValueException("Email is null or empty.");
        } else if (isInvalid(password)) {
            throw new InvalidRequestValueException("Password is null or empty.");
        } else if (isInvalid(role.name())) {
            throw new InvalidRequestValueException("Role is null or empty.");
        }
    }

    private boolean isInvalid(String field) {
        return field == null || field.isEmpty();
    }
}
