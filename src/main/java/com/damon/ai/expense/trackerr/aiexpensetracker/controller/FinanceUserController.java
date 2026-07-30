package com.damon.ai.expense.trackerr.aiexpensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.damon.ai.expense.trackerr.aiexpensetracker.service.IpAdressService;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.FinanceUser;
import com.damon.ai.expense.trackerr.aiexpensetracker.service.FinanceUserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/financeusers")
@Tag(name = "User API", description = "User Registration")
public class FinanceUserController {

    @Autowired
    private FinanceUserService service;

    public IpAdressService ipAdressService;

    

    private static final Logger logger = LoggerFactory.getLogger(FinanceUserController.class);

    // @Operation(summary = "register user")
    @PostMapping("/register")
    public FinanceUser register(
            @Valid @RequestBody FinanceUser user, HttpServletRequest request) {
       
       
                logger.info("Request received from IP: {}", request.getRemoteAddr());
       
        
        return service.register(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/users")
    public List<FinanceUser> getAllUsers() {
        return service.getAllUsers();
    }
}