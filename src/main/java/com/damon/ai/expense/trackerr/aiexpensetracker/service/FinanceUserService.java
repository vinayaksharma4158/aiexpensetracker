package com.damon.ai.expense.trackerr.aiexpensetracker.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.FinanceUser;
import com.damon.ai.expense.trackerr.aiexpensetracker.repository.FinanceUserRepository;
import com.damon.ai.expense.trackerr.aiexpensetracker.util.GenerateUserID;
import com.damon.ai.expense.trackerr.aiexpensetracker.exception.*;

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

    public FinanceUser findByEmail(String email) {
    return repository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
}


public FinanceUser updateUserIdByEmail(String email, String newUserId) {
    FinanceUser user = repository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));


         // Check if newUserId is already taken by another user
    if (repository.existsByUserId(newUserId)) {
           throw new UserIdAlreadyExistsException("UserId already exists: " + newUserId);
        }


    user.setUserId(newUserId); // ⚠️ careful if this is PK
    
    return repository.save(user);
}



}
