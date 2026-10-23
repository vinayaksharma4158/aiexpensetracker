package com.damon.ai.expense.trackerr.aiexpensetracker.repository;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpendingRecordRepository extends JpaRepository<SpendingRecord, Long> {
    List<SpendingRecord> findByOwner(UserProfile owner);
}
