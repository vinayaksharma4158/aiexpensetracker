package com.damon.ai.expense.trackerr.aiexpensetracker.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "custom_user_token_set")
public class CustomUserTokenSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", columnDefinition = "TEXT")
    private String token;

    @Column(name = "email")
    private String email;

    @Column(name = "is_valid_token", nullable = false)
    private Boolean isValidToken = true;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public CustomUserTokenSet() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsValidToken() {
        return isValidToken;
    }

    public void setIsValidToken(Boolean isValidToken) {
        this.isValidToken = isValidToken;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}