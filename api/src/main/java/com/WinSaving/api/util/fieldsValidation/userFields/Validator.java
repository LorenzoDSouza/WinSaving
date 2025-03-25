package com.WinSaving.api.util.fieldsValidation.userFields;

public interface Validator<T> {
    boolean validate(T input);
}
