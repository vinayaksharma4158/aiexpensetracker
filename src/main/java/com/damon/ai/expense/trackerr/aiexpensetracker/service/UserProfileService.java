package com.damon.ai.expense.trackerr.aiexpensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.UserProfile;
import com.damon.ai.expense.trackerr.aiexpensetracker.repository.UserProfileRepository;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository repo;

    public UserProfile findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public UserProfile updateProfileId(UserProfile user, String newId) {
        user.setProfileId(newId);
        return repo.save(user);
    }
}
