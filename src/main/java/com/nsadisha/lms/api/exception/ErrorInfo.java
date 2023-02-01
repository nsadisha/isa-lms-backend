package com.nsadisha.lms.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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
    private final HttpServletResponse httpRes;

    Date timestamp = new Date();

    public Map<String, ?> asMap() {
        return Map.of(
                "method", httpReq.getMethod(),
//                "status", httpRes.getStatus(),
                "path", httpReq.getServletPath(),
                "timestamp", timestamp,
                "message", e.getLocalizedMessage()
        );
    }
    public Map<String, ?> asMap(String message) {
        return Map.of(
                "method", httpReq.getMethod(),
//                "status", httpRes.getStatus(),
                "path", httpReq.getServletPath(),
                "timestamp", timestamp,
                "message", message
        );
    }
}
