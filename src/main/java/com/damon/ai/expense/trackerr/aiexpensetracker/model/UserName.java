package com.damon.ai.expense.trackerr.aiexpensetracker.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "user_personal_new_id")
public class UserName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String email;

    private String userId;

    private String newUserId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // GETTER AND SETTERS

    public Long getAccountId() {
        return accountId;
    }

    public String getUserId() {
        return userId;
    }

    public String getNewUserId() {
        return newUserId;
    }

    public String setNewUserId() {
        return newUserId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
