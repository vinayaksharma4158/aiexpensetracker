package com.damon.ai.expense.trackerr.aiexpensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.FinanceUser;
import com.damon.ai.expense.trackerr.aiexpensetracker.repository.FinanceUserRepository;
import com.damon.ai.expense.trackerr.aiexpensetracker.util.GenerateUserID;
import com.damon.ai.expense.trackerr.aiexpensetracker.exception.EmailAlreadyExistsException;

@Service
public class FinanceUserService {

    @Autowired
    private FinanceUserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public GenerateUserID generateUserID_new = new GenerateUserID();

    public FinanceUser register(FinanceUser user) {

        String generate = generateUserID_new.generateUserId();
        user.setUserId(generate);
        String email = user.getEmail().trim().toLowerCase();
        user.setEmail(email);
        user.setActive(true);
        user.setCreatedAt(java.time.LocalDateTime.now());
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        // user.setPassword("{bcrypt}" + passwordEncoder.encode(user.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (repository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        return repository.save(user);
    }

    public List<FinanceUser> getAllUsers() {
        return repository.findAll();
    }
}
