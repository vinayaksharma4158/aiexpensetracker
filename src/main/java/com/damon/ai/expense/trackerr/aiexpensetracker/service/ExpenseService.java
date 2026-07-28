package com.damon.ai.expense.trackerr.aiexpensetracker.service;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.Expense;
import com.damon.ai.expense.trackerr.aiexpensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    // ADD EXPENSE
    public Expense addExpense(Expense expense, String userEmail) {
        expense.setUserEmail(userEmail);
        return repository.save(expense);
    }

    // GET ALL USER EXPENSES
    public List<Expense> getUserExpenses(String userEmail) {
        return repository.findByUserEmail(userEmail);
    }

    // DELETE
    public void deleteExpense(Long id) {
        repository.deleteById(id);
    }

    public Expense updateExpense(Long id, Expense expense, String email) {

        Expense existingExpense = repository
                .findByIdAndUserEmail(id, email)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        existingExpense.setAmount(expense.getAmount());
        existingExpense.setCategory(expense.getCategory());
        existingExpense.setDescription(expense.getDescription());

        return repository.save(existingExpense);
    }

    public String deleteExpense(Long id, String email) {

        Expense expense = repository
                .findByIdAndUserEmail(id, email)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        repository.delete(expense);

        return "Expense Deleted Successfully";
    }
}
