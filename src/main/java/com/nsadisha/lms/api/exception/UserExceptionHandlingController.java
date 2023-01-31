package com.nsadisha.lms.api.exception;

import jakarta.security.auth.message.callback.SecretKeyCallback;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;


/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/
@ControllerAdvice @Slf4j
public class UserExceptionHandlingController {
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public String userNotFound(Exception e) {

        return e.getLocalizedMessage();
    }
}
