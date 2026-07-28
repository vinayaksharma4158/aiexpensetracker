package com.damon.ai.expense.trackerr.aiexpensetracker.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.damon.ai.expense.trackerr.aiexpensetracker.dto.UserIdUpdateRequest;

import com.damon.ai.expense.trackerr.aiexpensetracker.service.NewUserIDUpdationService;

@RestController
@RequestMapping("/api/users")
public class NewUserIDUpdationController {

    @Autowired
    private NewUserIDUpdationService userService;

    @PostMapping("/update-userid")
    public ResponseEntity<?> updateUserId(
            @RequestBody UserIdUpdateRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        String response = userService.updateUserId(email, request.getUserId());

        return ResponseEntity.ok(response);
    }
}
