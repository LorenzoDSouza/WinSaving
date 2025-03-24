package com.WinSaving.api.util.fieldsValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator<String> {
    public static final Pattern emailPattern =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean validate(String email) {
        Matcher matcher = emailPattern.matcher(email);

        return matcher.matches();
    }
}
