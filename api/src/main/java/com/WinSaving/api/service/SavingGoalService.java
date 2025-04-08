package com.WinSaving.api.service;

import com.WinSaving.api.repositories.SavingGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingGoalService {

    private final SavingGoalRepository savingGoalRepository;

    @Autowired
    public SavingGoalService(SavingGoalRepository savingGoalRepository) {
        this.savingGoalRepository = savingGoalRepository;
    }



}
