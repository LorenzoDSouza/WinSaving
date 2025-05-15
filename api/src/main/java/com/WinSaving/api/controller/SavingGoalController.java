package com.WinSaving.api.controller;

import com.WinSaving.api.domain.savingGoal.SavingGoal;
import com.WinSaving.api.domain.savingGoal.SavingGoalRequestDTO;
import com.WinSaving.api.service.SavingGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/saving-goals")
public class SavingGoalController {

    private final SavingGoalService savingGoalService;

    @Autowired
    public SavingGoalController(SavingGoalService savingGoalService) {
        this.savingGoalService = savingGoalService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<SavingGoal> createSavingGoal(@PathVariable UUID userId, @RequestBody SavingGoalRequestDTO dto) {
        SavingGoal savingGoal = savingGoalService.createSavingGoal(dto, userId);
        return ResponseEntity.ok(savingGoal);
    }

    @PatchMapping("/{savingGoalId}/name")
    public ResponseEntity<SavingGoal> updateName(@PathVariable UUID savingGoalId, @RequestBody Map<String, String> updates) {
        if (!updates.containsKey("name")) {
            return ResponseEntity.badRequest().build();
        }
        SavingGoal savingGoal = savingGoalService.updateName(updates.get("name"), savingGoalId);
        return ResponseEntity.ok(savingGoal);
    }

    @PatchMapping("/{savingGoalId}/purpose")
    public ResponseEntity<SavingGoal> updatePurpose(@PathVariable UUID savingGoalId, @RequestBody Map<String, String> updates) {
        if (!updates.containsKey("purpose")) {
            return ResponseEntity.badRequest().build();
        }
        SavingGoal savingGoal = savingGoalService.updatePurpose(updates.get("purpose"), savingGoalId);
        return ResponseEntity.ok(savingGoal);
    }

    @PatchMapping("/{savingGoalId}/deposit-place")
    public ResponseEntity<SavingGoal> updateDepositPlace(@PathVariable UUID savingGoalId, @RequestBody Map<String, String> updates) {
        if (!updates.containsKey("depositPlace")) {
            return ResponseEntity.badRequest().build();
        }
        SavingGoal savingGoal = savingGoalService.updateDepositPlace(updates.get("depositPlace"), savingGoalId);
        return ResponseEntity.ok(savingGoal);
    }

    @PatchMapping("/{savingGoalId}/due-date")
    public ResponseEntity<SavingGoal> updateDueDate(@PathVariable UUID savingGoalId, @RequestBody Map<String, Date> updates) {
        if (!updates.containsKey("dueDate")) {
            return ResponseEntity.badRequest().build();
        }
        SavingGoal savingGoal = savingGoalService.updateDueDate(updates.get("dueDate"), savingGoalId);
        return ResponseEntity.ok(savingGoal);
    }

    @PatchMapping("/{savingGoalId}/goal-amount")
    public ResponseEntity<SavingGoal> updateGoalAmount(@PathVariable UUID savingGoalId, @RequestBody Map<String, BigDecimal> updates) {
        if (!updates.containsKey("goalAmount")) {
            return ResponseEntity.badRequest().build();
        }
        SavingGoal savingGoal = savingGoalService.updateGoalAmount(updates.get("goalAmount"), savingGoalId);
        return ResponseEntity.ok(savingGoal);
    }

    @PatchMapping("/{savingGoalId}/total-amount")
    public ResponseEntity<SavingGoal> updateTotalAmount(@PathVariable UUID savingGoalId, @RequestBody Map<String, BigDecimal> updates) {
        if (!updates.containsKey("totalAmount")) {
            return ResponseEntity.badRequest().build();
        }
        SavingGoal savingGoal = savingGoalService.updateTotalAmount(updates.get("totalAmount"), savingGoalId);
        return ResponseEntity.ok(savingGoal);
    }

    @DeleteMapping("/{savingGoalId}")
    public ResponseEntity<Void> deleteSavingGoal(@PathVariable UUID savingGoalId) {
        savingGoalService.deleteSavingGoal(savingGoalId);
        return ResponseEntity.ok().build();
    }
}
