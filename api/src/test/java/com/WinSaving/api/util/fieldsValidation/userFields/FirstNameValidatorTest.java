package com.WinSaving.api.util.fieldsValidation.userFields;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FirstNameValidatorTest {

    private FirstNameValidator firstNameValidator;

    @BeforeEach
    public void setUp() {
        firstNameValidator = new FirstNameValidator();
    }

    @Test
    public void testIsValid_shouldReturnTrue_whenNameIsValid() {
        assertTrue(firstNameValidator.isValidPattern("Carlos"));
        assertTrue(firstNameValidator.isValidPattern("Érica"));
        assertTrue(firstNameValidator.isValidPattern("O'Connor"));
        assertTrue(firstNameValidator.isValidPattern("Jean-Luc"));
    }
}
