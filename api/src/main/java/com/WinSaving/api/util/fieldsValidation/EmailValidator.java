package com.WinSaving.api.util.fieldsValidation;

import com.WinSaving.api.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator implements Validator<String> {
    private final UserRepository userRepository;

    public static final Pattern emailPattern =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public EmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean validate(String email) {
        return isEmptyEmail(email) && isValidPattern(email) && isUniqueEmail(email);
    }

    public boolean isEmptyEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty!");
        }
        return true;
    }

    public boolean isValidPattern(String email) {
        if(!emailPattern.matcher(email).matches()){
            throw new IllegalArgumentException("The pattern of the email is invalid!");
        }
        return true;
    }

    private boolean isUniqueEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered!");
        }
        return true;
    }
}
