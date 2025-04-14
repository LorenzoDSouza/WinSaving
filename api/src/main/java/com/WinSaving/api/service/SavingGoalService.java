package com.WinSaving.api.service;

import com.WinSaving.api.domain.savingGoal.SavingGoal;
import com.WinSaving.api.domain.savingGoal.SavingGoalRequestDTO;
import com.WinSaving.api.domain.user.User;
import com.WinSaving.api.exceptions.UserNotFoundException;
import com.WinSaving.api.repositories.SavingGoalRepository;
import com.WinSaving.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class SavingGoalService {

    private final SavingGoalRepository savingGoalRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public SavingGoalService(SavingGoalRepository savingGoalRepository, UserService userService, UserRepository userRepository) {
        this.savingGoalRepository = savingGoalRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Transactional
    public SavingGoal createSavingGoal(SavingGoalRequestDTO dto, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        if (dto.goalAmount() == null) {
            throw new IllegalArgumentException("Goal amount is required");
        }
        if (dto.goalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Goal amount must be greater than zero");
        }

        if (dto.dueDate() != null && dto.dueDate().before(Date.valueOf(LocalDate.now()))) {
            throw new IllegalArgumentException("Due date cannot be in the past");
        }


        SavingGoal savingGoal = new SavingGoal();
        savingGoal.setUser(user);
        savingGoal.setGoalAmount(dto.goalAmount());
        savingGoal.setName(dto.name());
        savingGoal.setPurpose(dto.purpose());
        savingGoal.setDepositPlace(dto.depositPlace());
        savingGoal.setDueDate(dto.dueDate());

        return savingGoalRepository.save(savingGoal);
    }

}
