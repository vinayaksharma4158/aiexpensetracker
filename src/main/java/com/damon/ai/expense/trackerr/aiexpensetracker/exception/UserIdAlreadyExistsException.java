package com.damon.ai.expense.trackerr.aiexpensetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
public class UserIdAlreadyExistsException extends RuntimeException {
    public UserIdAlreadyExistsException(String message) {
        super(message);
    }
}
