package com.WinSaving.api.util;

public interface Validator<T> {
    boolean validate(T input);
}
