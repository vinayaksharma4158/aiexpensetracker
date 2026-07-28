package com.damon.ai.expense.trackerr.aiexpensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.FinanceUser;
import com.damon.ai.expense.trackerr.aiexpensetracker.service.FinanceUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/financeusers")
public class FinanceUserController {

    @Autowired
    private FinanceUserService service;

    @PostMapping("/register")
    public FinanceUser register(
            @Valid @RequestBody FinanceUser user) {

        return service.register(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/users")
    public List<FinanceUser> getAllUsers() {
        return service.getAllUsers();
    }
}