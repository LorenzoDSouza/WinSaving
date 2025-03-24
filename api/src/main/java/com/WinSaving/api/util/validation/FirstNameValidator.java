package com.WinSaving.api.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FirstNameValidator implements Validator<String> {

    public static final Pattern firstNamePattern =
            Pattern.compile("^[A-Za-zÀ-ÿ]+(?:[ '-][A-Za-zÀ-ÿ]+)*$");

    @Override
    public boolean validate(String input) {
        Matcher matcher = firstNamePattern.matcher(input);

        return matcher.matches();
    }

}
