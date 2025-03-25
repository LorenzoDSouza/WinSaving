package com.WinSaving.api.util.fieldsValidation.userFields;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class PhoneNumberValidator implements Validator<String> {
    private static final Pattern phoneNumberPattern =  Pattern.compile(
           "\\(?\\d{2}\\)?\\s?9\\d{4}-?\\d{4}|\\d{10}");


    @Override
    public boolean validate(String phoneNumber){
        return isNotEmpty(phoneNumber) && isValidPattern(phoneNumber);
    }

    public boolean isNotEmpty(String phoneNumber){
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone Number cannot be empty!");
        }
        return true;
    }

    public boolean isValidPattern(String phoneNumber){
        if(!phoneNumberPattern.matcher(phoneNumber).matches()){
            throw new IllegalArgumentException("The pattern of the phone number is invalid!");
        }
        return true;
    }
}
