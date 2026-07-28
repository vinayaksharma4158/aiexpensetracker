package com.damon.ai.expense.trackerr.aiexpensetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.FinanceUser;

@Repository
public interface FinanceUserRepository extends JpaRepository<FinanceUser, Long> {

    Optional<FinanceUser> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUserId(String userId);
}