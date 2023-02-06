package com.nsadisha.lms.api.exception;

/**
 * @author Sadisha Nimsara
 * @created 06 of Feb 2023
 **/
public class EmailAlreadyInUseException extends Exception{
    public EmailAlreadyInUseException(String msg) {
        super(msg);
    }
}
