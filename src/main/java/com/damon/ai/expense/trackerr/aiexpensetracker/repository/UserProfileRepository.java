package com.damon.ai.expense.trackerr.aiexpensetracker.repository;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByEmail(String email);
}
