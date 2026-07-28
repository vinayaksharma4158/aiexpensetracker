package com.damon.ai.expense.trackerr.aiexpensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.FinanceUser;

import com.damon.ai.expense.trackerr.aiexpensetracker.repository.FinanceUserRepository;

@Service
public class NewUserIDUpdationService {

    @Autowired
    private FinanceUserRepository userRepository;

    public String updateUserId(String email, String newUserId) {

        if (userRepository.existsByUserId(newUserId)) {
            throw new RuntimeException("User ID already exists");
        }

        FinanceUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserId(newUserId);

        userRepository.save(user);

        return "User ID updated successfully";
    }
}
