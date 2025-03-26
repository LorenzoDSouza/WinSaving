package com.WinSaving.api.util.fieldsValidation.userFields;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testIsValid_shouldThrowException_whenLastNameIsInvalid_onlyNumbers() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            lastNameValidator.isValidPattern("123");
        });
        assertEquals(thrown.getMessage(), "Last Name can only contain letters, spaces, apostrophes, and hyphens!");
    }

    @Test
    public void testIsValid_shouldThrowException_whenLastNameIsInvalid_lettersAndNumbers() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            lastNameValidator.isValidPattern("Eu1");
        });
        assertEquals(thrown.getMessage(), "Last Name can only contain letters, spaces, apostrophes, and hyphens!");
    }

    @Test
    public void testIsValid_shouldThrowException_whenLastNameIsInvalid_lettersAndSymbols() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            lastNameValidator.isValidPattern("Eu$");
        });
        assertEquals(thrown.getMessage(), "Last Name can only contain letters, spaces, apostrophes, and hyphens!");
    }

    @Test
    public void testIsNotEmpty_shouldThrowException_whenLastNameIsEmpty() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            lastNameValidator.isNotEmpty("");
        });
        assertEquals(thrown.getMessage(), "Last Name cannot be empty!");
    }

    @Test
    public void testIsNotEmpty_shouldThrowException_whenLastNameIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            lastNameValidator.isNotEmpty(null);
        });
        assertEquals(thrown.getMessage(), "Last Name cannot be empty!");
    }



}
