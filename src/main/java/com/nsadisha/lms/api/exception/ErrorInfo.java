package com.nsadisha.lms.api.exception;

import lombok.RequiredArgsConstructor;

import java.security.Timestamp;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/

public class ErrorInfo {
//    private Timestamp timestamp;
//    private int status;
//    private String error;
    private final String message;
//    private String path;

    public ErrorInfo(Exception e) {
        this.message= e.getLocalizedMessage();
    }
}
