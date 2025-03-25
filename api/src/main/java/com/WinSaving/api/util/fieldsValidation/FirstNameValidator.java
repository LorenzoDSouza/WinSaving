package com.WinSaving.api.util.fieldsValidation;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class FirstNameValidator implements Validator<String> {

    public static final Pattern firstNamePattern =
            Pattern.compile("^[A-Za-zÀ-ÿ]+(?:[ '-][A-Za-zÀ-ÿ]+)*$");

    @Override
    public boolean validate(String firstName) {
        return isNotEmpty(firstName) && isValidPattern(firstName);
    }

    public boolean isNotEmpty(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First Name cannot be empty!");
        }
        return true;
    }
    public boolean isValidPattern(String firstName){
        if(!firstNamePattern.matcher(firstName).matches()){
            throw new IllegalArgumentException("First name can only contain letters, spaces, apostrophes, and hyphens!");
        }
        return true;
    }
}
