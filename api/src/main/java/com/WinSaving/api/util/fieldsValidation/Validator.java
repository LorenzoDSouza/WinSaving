package com.WinSaving.api.util.fieldsValidation;

public interface Validator<T> {
    boolean validate(T input);
}
