package com.damon.ai.expense.trackerr.aiexpensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.SpendingRecord;
import com.damon.ai.expense.trackerr.aiexpensetracker.model.UserProfile;
import com.damon.ai.expense.trackerr.aiexpensetracker.repository.SpendingRecordRepository;

import java.util.List;

@Service
public class SpendingRecordService {
    @Autowired
    private SpendingRecordRepository repo;

    public List<SpendingRecord> getRecordsForUser(UserProfile user) {
        return repo.findByOwner(user);
    }

    public SpendingRecord addRecord(UserProfile user, SpendingRecord record) {
        record.setOwner(user);
        return repo.save(record);
    }
}
