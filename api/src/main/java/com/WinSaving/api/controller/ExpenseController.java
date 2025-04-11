package com.WinSaving.api.controller;

import com.WinSaving.api.domain.expense.Expense;
import com.WinSaving.api.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

}
