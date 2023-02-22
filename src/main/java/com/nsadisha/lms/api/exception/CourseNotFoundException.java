package com.nsadisha.lms.api.exception;

/**
 * @author Sadisha Nimsara
 * @created 22 of Feb 2023
 **/
public class CourseNotFoundException extends Exception {
    public CourseNotFoundException(String msg) {
        super(msg);
    }
}
