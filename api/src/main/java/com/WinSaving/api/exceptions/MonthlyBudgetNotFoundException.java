package com.WinSaving.api.exceptions;

public class MonthlyBudgetNotFoundException extends RuntimeException {
    public MonthlyBudgetNotFoundException(String message) {
        super(message);
    }
}
