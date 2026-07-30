package com.damon.ai.expense.trackerr.aiexpensetracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import com.damon.ai.expense.trackerr.aiexpensetracker.service.IpAdressService;

public class IpAddressUserController {

    IpAdressService ipAdressService;

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        return "Client IP: " + ipAddress;
    }

    @GetMapping("/ip")
    public String getIp(HttpServletRequest request) {
        return ipAdressService.getClientIp(request);
    }

}
