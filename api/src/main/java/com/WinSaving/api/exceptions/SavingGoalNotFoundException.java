package com.WinSaving.api.exceptions;

public class SavingGoalNotFoundException extends RuntimeException {
    public SavingGoalNotFoundException(String message) {
        super(message);
    }
}
