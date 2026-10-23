package com.damon.ai.expense.trackerr.aiexpensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.UserProfile;
import com.damon.ai.expense.trackerr.aiexpensetracker.service.UserProfileService;
import com.damon.ai.expense.trackerr.aiexpensetracker.util.JwtUtil;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/aiwallet/api/users")
public class UserProfileController {
    @Autowired
    private UserProfileService service;

    @GetMapping("/me")
    public UserProfile getMe(Authentication authentication) {
        String email = authentication.getName();
        return service.findByEmail(email);
    }

    @PutMapping("/updateProfileId")
    public UserProfile updateProfileId(Authentication authentication,
            @RequestParam String newId) {
        String email = authentication.getName();
        UserProfile user = service.findByEmail(email);
        return service.updateProfileId(user, newId);
    }

}