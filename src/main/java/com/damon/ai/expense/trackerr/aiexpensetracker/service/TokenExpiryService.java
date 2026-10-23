package com.damon.ai.expense.trackerr.aiexpensetracker.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.CustomUserTokenSet;
import com.damon.ai.expense.trackerr.aiexpensetracker.repository.CustomTokenRepository;

@Service
public class TokenExpiryService {

    private final CustomTokenRepository repository;

    public TokenExpiryService(CustomTokenRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 30000)
    @Transactional
    public void invalidateExpiredTokens() {
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(5);

        List<CustomUserTokenSet> tokens =
                repository.findByIsValidTokenTrueAndTimestampBefore(cutoff);

        for (CustomUserTokenSet token : tokens) {
            token.setIsValidToken(false);
        }

        repository.saveAll(tokens);
    }
}