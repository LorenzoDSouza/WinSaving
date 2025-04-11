package com.WinSaving.api.controller;

import com.WinSaving.api.domain.expense.Expense;
import com.WinSaving.api.domain.expense.ExpenseType;
import com.WinSaving.api.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PatchMapping("/{expenseId}/value")
    public ResponseEntity<Expense> updateValue(@PathVariable UUID expenseId, @RequestBody Map<String, BigDecimal> updates) {
        if (!updates.containsKey("value")){
            return ResponseEntity.badRequest().build();
        }

        Expense expense = expenseService.updateValue(expenseId, updates.get("value"));

        return ResponseEntity.ok(expense);
    }

    @PatchMapping("/{expenseId}/expense-type")
    public ResponseEntity<Expense> updateExpenseType(@PathVariable UUID expenseId, @RequestBody Map<String, ExpenseType> updates) {
        if (!updates.containsKey("expenseType")){
            return ResponseEntity.badRequest().build();
        }

        Expense expense = expenseService.updateExpenseType(expenseId, updates.get("expenseType"));

        return ResponseEntity.ok(expense);
    }

    @PatchMapping("/{expenseId}/description")
    public ResponseEntity<Expense> updateDescription(@PathVariable UUID expenseId, @RequestBody Map<String, String> updates) {
        if (!updates.containsKey("description")){
            return ResponseEntity.badRequest().build();
        }

        Expense expense = expenseService.updateDescription(expenseId, updates.get("description"));

        return ResponseEntity.ok(expense);
    }

    @PatchMapping("/{expenseId}/date")
    public ResponseEntity<Expense> updateDate(@PathVariable UUID expenseId, @RequestBody Map<String, Date> updates) {
        if (!updates.containsKey("date")){
            return ResponseEntity.badRequest().build();
        }

        Expense expense = expenseService.updateDate(expenseId, updates.get("date"));

        return ResponseEntity.ok(expense);
    }

}
