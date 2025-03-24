package com.WinSaving.api.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements Validator<String> {
    public static final Pattern passwordPattern =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[!@#$%^&*(),.?\\\":{}|<>])[A-Za-z\\\\d!@#$%^&*(),.?\\\":{}|<>]{8,}$");

    @Override
    public boolean validate(String input) {
        Matcher matcher = passwordPattern.matcher(input);

        return matcher.matches();
    }
}
