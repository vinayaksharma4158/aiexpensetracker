package com.damon.ai.expense.trackerr.aiexpensetracker.controller;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.Expense;
import com.damon.ai.expense.trackerr.aiexpensetracker.service.ExpenseService;

import com.damon.ai.expense.trackerr.aiexpensetracker.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @Autowired
    private JwtUtil jwtUtil;

    // ADD EXPENSE
    @PostMapping("/add")
    public Expense addExpense(
            @RequestBody Expense expense,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7); // remove "Bearer "
        String email = jwtUtil.extractUsername(token);

        return service.addExpense(expense, email);
    }

    // GET USER EXPENSES
    @GetMapping("/my")
    public List<Expense> getMyExpenses(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);

        return service.getUserExpenses(email);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expense,
            Authentication authentication) {

        String email = authentication.getName();

        return service.updateExpense(id, expense, email);
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(
            @PathVariable Long id,
            Authentication authentication) {

        String email = authentication.getName();

        return service.deleteExpense(id, email);
    }
}