package com.WinSaving.api.controller;

import com.WinSaving.api.service.SavingGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/savin-goals")
public class SavingGoalController {

    private final SavingGoalService savingGoalService;

    @Autowired
    public SavingGoalController(SavingGoalService savingGoalService) {
        this.savingGoalService = savingGoalService;
    }
}
