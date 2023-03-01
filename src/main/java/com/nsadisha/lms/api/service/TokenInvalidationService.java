package com.nsadisha.lms.api.service;

import java.util.HashSet;

/**
 * @author Sadisha Nimsara
 * @created 20 of Feb 2023
 **/

public class TokenInvalidationService {
    private final HashSet<String> blacklistedTokens;

    public TokenInvalidationService() {
        blacklistedTokens = new HashSet<>();
    }

    public boolean add(String token) {
        return blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}
