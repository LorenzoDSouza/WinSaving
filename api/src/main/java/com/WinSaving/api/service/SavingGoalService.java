package com.WinSaving.api.service;

import com.WinSaving.api.domain.savingGoal.SavingGoal;
import com.WinSaving.api.domain.savingGoal.SavingGoalRequestDTO;
import com.WinSaving.api.domain.user.User;
import com.WinSaving.api.exceptions.DateComparisonException;
import com.WinSaving.api.exceptions.SavingGoalNotFoundException;
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

    @Transactional
    public SavingGoal updateName(String name, UUID savingGoalId) {
        if(name == null){
            throw new IllegalArgumentException("Name is required to update saving goal!");
        }

        SavingGoal savingGoal = savingGoalRepository.findById(savingGoalId)
                .orElseThrow(() -> new SavingGoalNotFoundException("Saving Goal not found with id: " + savingGoalId));


        savingGoal.setName(name);
        return savingGoalRepository.save(savingGoal);
    }

    @Transactional
    public SavingGoal updatePurpose(String purpose, UUID savingGoalId) {
        if(purpose == null){
            throw new IllegalArgumentException("Purpose is required to update saving goal!");
        }

        SavingGoal savingGoal = savingGoalRepository.findById(savingGoalId)
                .orElseThrow(() -> new SavingGoalNotFoundException("Saving Goal not found with id: " + savingGoalId));

        savingGoal.setPurpose(purpose);
        return savingGoalRepository.save(savingGoal);
    }

    @Transactional
    public SavingGoal updateDepositPlace(String depositPlace, UUID savingGoalId){
        if(depositPlace == null){
            throw new IllegalArgumentException("Deposit Place is required to update saving goal!");
        }

        SavingGoal savingGoal = savingGoalRepository.findById(savingGoalId)
                .orElseThrow(() -> new SavingGoalNotFoundException("Saving Goal not found with id: " + savingGoalId));

        savingGoal.setDepositPlace(depositPlace);
        return savingGoalRepository.save(savingGoal);
    }

    @Transactional
    public SavingGoal updateDueDate(Date dueDate, UUID savingGoalId) {
        if(dueDate == null){
            throw new IllegalArgumentException("Due Date is required to update saving goal!");
        }

        SavingGoal savingGoal = savingGoalRepository.findById(savingGoalId)
                .orElseThrow(() -> new SavingGoalNotFoundException("Saving Goal not found with id: " + savingGoalId));

        if (savingGoal.getDueDate().compareTo(dueDate) == 0) {
            throw new IllegalArgumentException("New due date cannot be the same as the old one!");
        }

        if (dueDate.compareTo(new java.sql.Date(System.currentTimeMillis())) < 0) {
            throw new DateComparisonException("New due date cannot be in the past!");
        }

        savingGoal.setDueDate(dueDate);
        return savingGoalRepository.save(savingGoal);
    }

    @Transactional
    public SavingGoal updateGoalAmount(BigDecimal goalAmount, UUID savingGoalId) {
        if(goalAmount == null){
            throw new IllegalArgumentException("Goal amount is required to update saving goal!");
        }

        if (goalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Goal amount must be greater than zero");
        }

        SavingGoal savingGoal = savingGoalRepository.findById(savingGoalId)
                .orElseThrow(() -> new SavingGoalNotFoundException("Saving Goal not found with id: " + savingGoalId));

        if (savingGoal.getGoalAmount().compareTo(goalAmount) == 0) {
            throw new IllegalArgumentException("New goal amount cannot be the same as the old one!");
        }

        savingGoal.setGoalAmount(goalAmount);
        return savingGoalRepository.save(savingGoal);
    }

    @Transactional
    public SavingGoal updateTotalAmount(BigDecimal totalAmount, UUID savingGoalId) {
        if(totalAmount == null){
            throw new IllegalArgumentException("Total amount is required to update saving goal!");
        }
        if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Total amount must be greater than zero");
        }

        SavingGoal savingGoal = savingGoalRepository.findById(savingGoalId)
                .orElseThrow(() -> new SavingGoalNotFoundException("Saving Goal not found with id: " + savingGoalId));

        savingGoal.setTotalAmount(totalAmount);
        return savingGoalRepository.save(savingGoal);
    }

    @Transactional
    public void deleteSavingGoal(UUID savingGoalId) {
        SavingGoal savingGoal = savingGoalRepository.findById(savingGoalId)
                .orElseThrow(() -> new SavingGoalNotFoundException("Saving Goal not found with id: " + savingGoalId));

        savingGoalRepository.delete(savingGoal);
    }
}
