package com.damon.ai.expense.trackerr.aiexpensetracker.repository;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}