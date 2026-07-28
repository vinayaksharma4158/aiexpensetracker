package com.damon.ai.expense.trackerr.aiexpensetracker.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.AuditLog;
import com.damon.ai.expense.trackerr.aiexpensetracker.repository.AuditLogRepository;

@Service
public class AuditService {

    private final AuditLogRepository repository;

    public AuditService(AuditLogRepository repository) {
        this.repository = repository;
    }

    public void saveAudit(
            String userEmail,
            String action,
            String endpoint,
            String method,
            Integer statusCode) {

        AuditLog log = new AuditLog();

        log.setUserEmail(userEmail);
        log.setAction(action);
        log.setEndpoint(endpoint);
        log.setMethod(method);
        log.setStatusCode(statusCode);
        log.setTimestamp(LocalDateTime.now());

        repository.save(log);
    }
}
