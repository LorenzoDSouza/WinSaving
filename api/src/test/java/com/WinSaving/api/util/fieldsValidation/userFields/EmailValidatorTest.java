package com.WinSaving.api.util.fieldsValidation.userFields;

import com.WinSaving.api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmailValidatorTest {

    private EmailValidator emailValidator;
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        emailValidator = new EmailValidator(userRepository);
    }

    @Test
    public void testIsNotEmpty_shouldThrowException_whenEmailIsEmpty() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            emailValidator.isNotEmpty("");
        });
        assertEquals("Email cannot be empty!", thrown.getMessage());
    }

    @Test
    public void testIsNotEmpty_shouldThrowException_whenEmailIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            emailValidator.isNotEmpty(null);
        });
        assertEquals("Email cannot be empty!", thrown.getMessage());
    }

    @Test
    public void testIsNotEmpty_shouldReturnTrue_whenEmailIsNotEmpty() {
        assertTrue(emailValidator.isNotEmpty("test@example.com"));
    }

    @Test
    public void testIsValidPattern_shouldThrowException_whenEmailHasInvalidPattern() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            emailValidator.isValidPattern("invalid-email");
        });
        assertEquals("The pattern of the email is invalid!", thrown.getMessage());
    }

    @Test
    public void testIsValidPattern_shouldReturnTrue_whenEmailHasValidPattern() {
        assertTrue(emailValidator.isValidPattern("test@example.com"));
    }

    @Test
    public void testIsUniqueEmail_shouldThrowException_whenEmailIsAlreadyRegistered() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            emailValidator.isUniqueEmail("test@example.com");
        });
        assertEquals("Email already registered!", thrown.getMessage());
    }

    @Test
    public void testIsUniqueEmail_shouldReturnTrue_whenEmailIsNotRegistered() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);

        assertTrue(emailValidator.isUniqueEmail("test@example.com"));
    }

    @Test
    public void testValidate_shouldThrowException_whenEmailIsEmpty() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            emailValidator.validate("");
        });
        assertEquals("Email cannot be empty!", thrown.getMessage());
    }

    @Test
    public void testValidate_shouldThrowException_whenEmailHasInvalidPattern() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            emailValidator.validate("invalid-email");
        });
        assertEquals("The pattern of the email is invalid!", thrown.getMessage());
    }

    @Test
    public void testValidate_shouldThrowException_whenEmailIsAlreadyRegistered() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            emailValidator.validate("test@example.com");
        });
        assertEquals("Email already registered!", thrown.getMessage());
    }

    @Test
    public void testValidate_shouldReturnTrue_whenEmailIsValidAndUnique() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);

        assertTrue(emailValidator.validate("test@example.com"));
    }
}
