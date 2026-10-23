package com.damon.ai.expense.trackerr.aiexpensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.OtpGenerateValidate;
import com.damon.ai.expense.trackerr.aiexpensetracker.model.UserProfile;
import com.damon.ai.expense.trackerr.aiexpensetracker.service.OtpService;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/otp")
public class CustomOtpController {

    @Autowired
    private OtpService otpService;

    @GetMapping("/generate-otp")
    public String getMe(Authentication authentication) {
        String email = authentication.getName();             
        return otpService.generateOTP(email);
    }
    
 // Endpoint to validate OTP
    @PostMapping("/validate")
    public ResponseEntity<String> validateOtp(Authentication authentication,
                                              @RequestParam String otp) {
    	 String email = authentication.getName();  
        boolean isValid = otpService.validateOtp(email, otp);
        if (isValid) {
            return ResponseEntity.ok("OTP is valid");
        } else {
            return ResponseEntity.badRequest().body("OTP is invalid or expired");
        }
    }

}
