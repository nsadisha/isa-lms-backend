package com.nsadisha.lms.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/

@RequiredArgsConstructor
public class ErrorInfo {
    private final Exception e;
    private final HttpServletRequest httpReq;

    LocalDateTime timestamp = LocalDateTime.now();

    public Map<String, ?> asMap() {
        return Map.of(
                "method", httpReq.getMethod(),
                "path", httpReq.getServletPath(),
                "timestamp", timestamp.toString(),
                "message", e.getLocalizedMessage()
        );
    }
    public Map<String, ?> asMap(String message) {
        return Map.of(
                "method", httpReq.getMethod(),
                "path", httpReq.getServletPath(),
                "timestamp", timestamp.toString(),
                "message", message
        );
    }

    @Override
    public String toString() {
        return toString(e.getLocalizedMessage());
    }

    public String toString(String message) {
        return "{" +
                "\"method\": \""+httpReq.getMethod()+
                "\",\"path\": \""+httpReq.getServletPath()+
                "\",\"timestamp\": \""+timestamp.toString()+
                "\",\"message\": \""+message+
                "\"}";
    }
}
