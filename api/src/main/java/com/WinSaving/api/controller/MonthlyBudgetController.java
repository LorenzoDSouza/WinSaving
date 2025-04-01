package com.WinSaving.api.controller;

import com.WinSaving.api.domain.monthlyBudget.MonthlyBudget;
import com.WinSaving.api.service.MonthlyBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/monthly-budgets")
public class MonthlyBudgetController {

    @Autowired
    private MonthlyBudgetService monthlyBudgetService;

    @PatchMapping("/{monthlyBudgetId}/original-amount")
    public ResponseEntity<MonthlyBudget> updateOriginalAmount(@PathVariable UUID monthlyBudgetId, @RequestBody Map<String, BigDecimal> updates) {
        if (!updates.containsKey("originalAmount")) {
            return ResponseEntity.badRequest().build();
        }

        MonthlyBudget updatedBudget = monthlyBudgetService.updateOriginalAmount(monthlyBudgetId, updates.get("originalAmount"));
        return ResponseEntity.ok(updatedBudget);
    }

    @PatchMapping("/{monthlyBudgetId}/used-amount")
    public ResponseEntity<MonthlyBudget> updateUsedAmount(@PathVariable UUID monthlyBudgetId, @RequestBody Map<String, BigDecimal> updates) {
        if (!updates.containsKey("usedAmount")) {
            return ResponseEntity.badRequest().build();
        }

        MonthlyBudget updatedBudget = monthlyBudgetService.updateUsedAmount(monthlyBudgetId, updates.get("usedAmount"));
        return ResponseEntity.ok(updatedBudget);
    }

    @PatchMapping("/{monthlyBudgetId}/pay-day")
    public ResponseEntity<MonthlyBudget> updatePayDay(@PathVariable UUID monthlyBudgetId, @RequestBody Map<String, Integer> updates) {
        if (!updates.containsKey("payDay")) {
            return ResponseEntity.badRequest().build();
        }

        MonthlyBudget updateBudget = monthlyBudgetService.updatePayDay(monthlyBudgetId, updates.get("payDay"));
        return ResponseEntity.ok(updateBudget);
    }

    @GetMapping("/{monthlyBudgetId}/reset-used-amount")
    public ResponseEntity<MonthlyBudget> resetOriginalAmount(@PathVariable UUID monthlyBudgetId) {
        MonthlyBudget updateBudget = monthlyBudgetService.renewUsedAmount(monthlyBudgetId);
        return ResponseEntity.ok(updateBudget);
    }
}
