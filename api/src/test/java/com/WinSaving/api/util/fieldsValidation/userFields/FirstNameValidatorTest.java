package com.WinSaving.api.util.fieldsValidation.userFields;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testIsEmpty_shouldThrowException_whenNameIsEmpty() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            firstNameValidator.isNotEmpty("");
        });
        assertEquals(thrown.getMessage(), "First Name cannot be empty!");
    }

    @Test
    public void testIsEmpty_shouldThrowException_whenNameIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            firstNameValidator.isNotEmpty("");
        });
        assertEquals(thrown.getMessage(), "First Name cannot be empty!");
    }
}
