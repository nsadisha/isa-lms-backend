package com.nsadisha.lms.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/
@ControllerAdvice @Slf4j
public class UserExceptionHandlingController {
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleUserNotFoundException(Exception e, HttpServletRequest httpReq) {
        ErrorInfo errorInfo = new ErrorInfo(e, httpReq);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo.asMap());
    }

    @ExceptionHandler(NullUserException.class)
    @ResponseBody
    public ResponseEntity<?> handleNullUserException(Exception e, HttpServletRequest httpReq) {
        ErrorInfo errorInfo = new ErrorInfo(e, httpReq);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorInfo.asMap());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<?> handleMessageNotReadableException(Exception e, HttpServletRequest httpReq) {
        ErrorInfo errorInfo = new ErrorInfo(e, httpReq);
        String message = e.getLocalizedMessage().split(":")[0];
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo.asMap(message));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleGlobalException(Exception e, HttpServletRequest httpReq) {
        ErrorInfo errorInfo = new ErrorInfo(e, httpReq);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfo.asMap());
    }
}
