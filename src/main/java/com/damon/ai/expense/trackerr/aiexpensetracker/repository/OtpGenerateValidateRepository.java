package com.damon.ai.expense.trackerr.aiexpensetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.OtpGenerateValidate;
import com.damon.ai.expense.trackerr.aiexpensetracker.model.UserName;

public interface OtpGenerateValidateRepository extends
        JpaRepository<OtpGenerateValidate, Long> {
	
	
	
	OtpGenerateValidate findTopByEmailOrderByTimestampDesc(String email);

}
