package com.damon.ai.expense.trackerr.aiexpensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.SpendingRecord;
import com.damon.ai.expense.trackerr.aiexpensetracker.model.UserProfile;
import com.damon.ai.expense.trackerr.aiexpensetracker.service.AiAdvisorService;
import com.damon.ai.expense.trackerr.aiexpensetracker.service.SpendingRecordService;
import com.damon.ai.expense.trackerr.aiexpensetracker.service.UserProfileService;
import com.damon.ai.expense.trackerr.aiexpensetracker.util.JwtUtil;
import org.springframework.security.core.Authentication;

import java.util.*;

@RestController
@RequestMapping("/aiwallet/api/records")
public class SpendingRecordController {
    @Autowired
    private SpendingRecordService recordService;
    @Autowired
    private UserProfileService userService;
    @Autowired
    private AiAdvisorService aiService;

    @PostMapping("/add")
    public SpendingRecord addRecord(Authentication authentication,
            @RequestBody SpendingRecord record) {
        // Extract token from header

        String email = authentication.getName();

        // Find user by email
        UserProfile user = userService.findByEmail(email);

        // Save record
        return recordService.addRecord(user, record);
    }

    @GetMapping("/all")
    public Map<String, Object> getAllRecords(Authentication authentication) {
        // Extract token from header
        String email = authentication.getName();

        // Fetch user and records
        UserProfile user = userService.findByEmail(email);
        List<SpendingRecord> records = recordService.getRecordsForUser(user);

        // Generate AI insights
        String aiInsight = aiService.generateInsights(records);

        // Build response
        Map<String, Object> response = new HashMap<>();
        response.put("records", records);
        response.put("aiInsight", aiInsight);
        return response;
    }

}