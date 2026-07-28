package com.damon.ai.expense.trackerr.aiexpensetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.UserName;

@Repository
public interface NewUserIDUpdationRepository extends JpaRepository<UserName, Long> {

    Optional<UserName> findByEmail(String email);

    boolean existsByEmail(String email);
}
