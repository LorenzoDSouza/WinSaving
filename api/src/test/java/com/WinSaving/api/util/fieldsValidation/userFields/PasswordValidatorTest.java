package com.WinSaving.api.util.fieldsValidation.userFields;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PasswordValidatorTest {

    private PasswordValidator passwordValidator;

    @BeforeEach
    public void setup() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    public void testValidPassords_shouldReturnTrue() {
        assertTrue(passwordValidator.validate("Enzo2015@"));
        assertTrue(passwordValidator.validate("123Keka$"));
    }

    @Test
    public void testValidPasswords_shouldThrowException_lessThanMinLength() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            passwordValidator.validate("Eu123@");
        });

        assertEquals(thrown.getMessage(), "Invalid password!" +
                " It must contain at least one lowercase letter, " +
                "one uppercase letter, " +
                "one number and " +
                "one special character");
    }

    @Test
    public void testValidPassword_shouldThrowException_withoutUppercaseLetter_withLowerCaseLetter_with8Digits_withSymbols_withNumbers() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            passwordValidator.validate("eutu1234$");
        });

        assertEquals(thrown.getMessage(), "Invalid password!" +
                " It must contain at least one lowercase letter, " +
                "one uppercase letter, " +
                "one number and " +
                "one special character");
    }

    @Test
    public void testValidPassword_shouldThrowException_withUppercaseLetter_withoutLowerCaseLetter_with8Digits_withSymbols_withNumbers() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            passwordValidator.validate("EUTU1234$");
        });

        assertEquals(thrown.getMessage(), "Invalid password!" +
                " It must contain at least one lowercase letter, " +
                "one uppercase letter, " +
                "one number and " +
                "one special character");
    }

    @Test
    public void testValidPassword_shouldThrowException_withUppercaseLetter_withLowerCaseLetter_with8Digits_withoutSymbols_withNumbers() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            passwordValidator.validate("EUtu12345");
        });

        assertEquals(thrown.getMessage(), "Invalid password!" +
                " It must contain at least one lowercase letter, " +
                "one uppercase letter, " +
                "one number and " +
                "one special character");
    }

    @Test
    public void testValidPassword_shouldThrowException_withUppercaseLetter_withLowerCaseLetter_with8Digits_withSymbols_withoutNumbers() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            passwordValidator.validate("EUtu@@@@");
        });

        assertEquals(thrown.getMessage(), "Invalid password!" +
                " It must contain at least one lowercase letter, " +
                "one uppercase letter, " +
                "one number and " +
                "one special character");
    }
}
