package com.WinSaving.api.repositories;

import com.WinSaving.api.domain.savingGoal.SavingGoal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SavingGoalRepository extends JpaRepository<SavingGoal, UUID> {
    @Transactional
    void deleteByUserId(UUID userId);


}
