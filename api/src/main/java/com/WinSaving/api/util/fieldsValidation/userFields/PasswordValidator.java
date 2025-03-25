package com.WinSaving.api.util.fieldsValidation.userFields;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements Validator<String> {
    public static final Pattern passwordPattern =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,}$");

    @Override
    public boolean validate(String password) {
        return isNotEmpty(password) && isValidPattern(password);
    }

    public boolean isNotEmpty(String password) {
        if(password == null || password.trim().isEmpty()){
            throw new IllegalArgumentException("Password cannot be null or empty!");
        }
        return true;
    }

    public boolean isValidPattern(String password) {
        if (!passwordPattern.matcher(password).matches()) {
            throw new IllegalArgumentException("Invalid password!" +
                    " It must contain at least one lowercase letter, " +
                    "one uppercase letter, " +
                    "one number and " +
                    "one special character");
        }
        return true;
    }
}
