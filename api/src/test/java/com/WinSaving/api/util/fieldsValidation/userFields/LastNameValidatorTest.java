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
        assertTrue(lastNameValidator.validate("Smith"));
        assertTrue(lastNameValidator.validate("D'Largy"));
        assertTrue(lastNameValidator.validate("Doe-Smith"));
        assertTrue(lastNameValidator.validate("Doe Smith"));
        assertTrue(lastNameValidator.validate("Sausage-Hausen"));
        assertTrue(lastNameValidator.validate("d'Arras"));
        assertTrue(lastNameValidator.validate("Luther King"));
        assertTrue(lastNameValidator.validate("Wong"));
        assertTrue(lastNameValidator.validate("Chang"));
        assertTrue(lastNameValidator.validate("Bara"));
    }

    @Test
    public void testIsValid_shouldThrowException_whenLastNameIsInvalid_onlyNumbers() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            lastNameValidator.validate("123");
        });
        assertEquals(thrown.getMessage(), "Last Name can only contain letters, spaces, apostrophes, and hyphens!");
    }

    @Test
    public void testIsValid_shouldThrowException_whenLastNameIsInvalid_lettersAndNumbers() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            lastNameValidator.validate("Eu1");
        });
        assertEquals(thrown.getMessage(), "Last Name can only contain letters, spaces, apostrophes, and hyphens!");
    }

    @Test
    public void testIsValid_shouldThrowException_whenLastNameIsInvalid_lettersAndSymbols() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            lastNameValidator.validate("Eu$");
        });
        assertEquals(thrown.getMessage(), "Last Name can only contain letters, spaces, apostrophes, and hyphens!");
    }

    @Test
    public void testIsNotEmpty_shouldThrowException_whenLastNameIsEmpty() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            lastNameValidator.validate("");
        });
        assertEquals(thrown.getMessage(), "Last Name cannot be empty!");
    }

    @Test
    public void testIsNotEmpty_shouldThrowException_whenLastNameIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            lastNameValidator.validate(null);
        });
        assertEquals(thrown.getMessage(), "Last Name cannot be empty!");
    }



}
