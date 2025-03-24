package com.WinSaving.api.util.validation;

public interface Validator<T> {
    boolean validate(T input);
}
