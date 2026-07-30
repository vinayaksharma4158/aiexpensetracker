package com.damon.ai.expense.trackerr.aiexpensetracker.service;

import jakarta.servlet.http.HttpServletRequest;

public class IpAdressService {

    public String getClientIp(HttpServletRequest request) {

        String xfHeader = request.getHeader("X-Forwarded-For");

        if (xfHeader == null || xfHeader.isBlank()) {
            return request.getRemoteAddr();
        }

        return xfHeader.split(",")[0].trim();
    }
}
