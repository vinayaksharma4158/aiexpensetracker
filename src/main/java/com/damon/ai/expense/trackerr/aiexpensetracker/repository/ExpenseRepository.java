package com.damon.ai.expense.trackerr.aiexpensetracker.repository;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<Expense> findByIdAndUserEmail(Long id, String userEmail);

    List<Expense> findByUserEmail(String userEmail);
}