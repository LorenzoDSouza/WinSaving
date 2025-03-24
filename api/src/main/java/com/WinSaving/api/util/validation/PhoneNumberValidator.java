package com.WinSaving.api.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements Validator<String> {

    private static final Pattern phoneNumberPattern =  Pattern.compile(
           "\\(?\\d{2}\\)?\\s?9\\d{4}-?\\d{4}|\\d{10}");

    @Override
    public boolean validate(String phoneNumber){
        Matcher matcher = phoneNumberPattern.matcher(phoneNumber);

        return matcher.matches();
    }


}
