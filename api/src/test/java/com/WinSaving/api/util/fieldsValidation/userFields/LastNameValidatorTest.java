package com.WinSaving.api.util.fieldsValidation.userFields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LastNameValidatorTest {

    private LastNameValidator lastNameValidator;

    @BeforeEach
    public void setUp() {
        lastNameValidator = new LastNameValidator();
    }

    @Test
    public void testIsValid_shouldReturnTrue_whenLastNameIsValid() {
        assertTrue(lastNameValidator.isValidPattern("Smith"));
        assertTrue(lastNameValidator.isValidPattern("D'Largy"));
        assertTrue(lastNameValidator.isValidPattern("Doe-Smith"));
        assertTrue(lastNameValidator.isValidPattern("Doe Smith"));
        assertTrue(lastNameValidator.isValidPattern("Sausage-Hausen"));
        assertTrue(lastNameValidator.isValidPattern("d'Arras"));
        assertTrue(lastNameValidator.isValidPattern("Luther King"));
        assertTrue(lastNameValidator.isValidPattern("Wong"));
        assertTrue(lastNameValidator.isValidPattern("Chang"));
        assertTrue(lastNameValidator.isValidPattern("Bara"));
    }




}
