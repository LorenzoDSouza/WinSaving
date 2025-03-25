package com.WinSaving.api.util.fieldsValidation.userFields;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class LastNameValidator implements Validator<String> {
    public static final Pattern lastNamePattern =
            Pattern.compile("^[A-Za-zÀ-ÿ]+(?:[ '-]?[A-Za-zÀ-ÿ]+)*(?:[ '-]de[A-Za-zÀ-ÿ]+)?$");


    @Override
    public boolean validate(String lastName) {
        return !isNotEmpty(lastName) && isValidPattern(lastName);
    }

    public boolean isNotEmpty(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last Name cannot be empty!");
        }
        return true;
    }
    public boolean isValidPattern(String lastName){
        if(!lastNamePattern.matcher(lastName).matches()){
            throw new IllegalArgumentException("Last Name can only contain letters, spaces, apostrophes, and hyphens!");
        }
        return true;
    }
}
