package com.nsadisha.lms.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

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

    Date timestamp = new Date();

    public Map<String, ?> asMap() {
        return Map.of(
                "method", httpReq.getMethod(),
                "path", httpReq.getServletPath(),
                "timestamp", timestamp,
                "message", e.getLocalizedMessage()
        );
    }
    public Map<String, ?> asMap(String message) {
        return Map.of(
                "method", httpReq.getMethod(),
                "path", httpReq.getServletPath(),
                "timestamp", timestamp,
                "message", message
        );
    }
}
