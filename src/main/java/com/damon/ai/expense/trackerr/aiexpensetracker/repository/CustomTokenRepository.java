package com.damon.ai.expense.trackerr.aiexpensetracker.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.CustomUserTokenSet;



public interface CustomTokenRepository  extends JpaRepository<CustomUserTokenSet, Long>{

	
	 List<CustomUserTokenSet> findByIsValidTokenTrueAndTimestampBefore(
	            LocalDateTime cutoff);
	 
	 
}
