package com.WinSaving.api.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LastNameValidator implements Validator<String> {
    public static final Pattern lastNamePattern =
            Pattern.compile("^[A-Za-zÀ-ÿ]+(?:[ '-]?[A-Za-zÀ-ÿ]+)*(?:[ '-]de[A-Za-zÀ-ÿ]+)?$");


    @Override
    public boolean validate(String lastName) {
        Matcher matcher = lastNamePattern.matcher(lastName);

        return matcher.matches();
    }
}
