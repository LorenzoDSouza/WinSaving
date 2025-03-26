package com.WinSaving.api.util.fieldsValidation.userFields;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PhoneNumberValidatorTest {

    private PhoneNumberValidator phoneNumberValidator;

    @BeforeEach
    public void setup() {
        phoneNumberValidator = new PhoneNumberValidator();
    }

    @Test
    public void testIsNotEmpty_shouldReturnTrue_whenPhoneNumberIsNotEmpty(){
        assertTrue(phoneNumberValidator.isNotEmpty("5551991757318"));
    }

    @Test
    public void testIsNotEmpty_shouldThrowException_whenPhoneNumberIsEmpty(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            phoneNumberValidator.isNotEmpty("");
        });
        assertEquals("Phone Number cannot be empty!", thrown.getMessage());
    }

    @Test
    public void testIsNotEmpty_shouldThrowException_whenPhoneNumberIsNull(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            phoneNumberValidator.isNotEmpty(null);
        });
        assertEquals("Phone Number cannot be empty!", thrown.getMessage());
    }

    @Test
    public void testIsValidPattern_shouldReturnTrue_whenPhoneNumberIsValid_completeNumberWithoutSpecialSymbols_noSpaces(){
        assertTrue(phoneNumberValidator.isValidPattern("5551991757318"));
    }

    @Test
    public void testIsValidPattern_shouldReturnTrue_whenPhoneNumberIsValid_completeNumberWithSpecialSymbols_noSpaces(){
        assertTrue(phoneNumberValidator.isValidPattern("+55(51)99175-7318"));
    }

    @Test
    public void testIsValidPattern_shouldReturnTrue_whenPhoneNumberIsValid_completeNumberWithSpecialSymbols_withSpaces(){
        assertTrue(phoneNumberValidator.isValidPattern("+55 (51) 99175-7318"));
    }

    @Test
    public void testIsValidPattern_shouldReturnTrue_whenPhoneNumberIsValid_completeNumberWithSpecialSymbols_withSpaces_spaceReplacingHyphen(){
        assertTrue(phoneNumberValidator.isValidPattern("+55 (51) 99175 7318"));
    }

    @Test
    public void testIsValidPattern_shouldReturnTrue_whenPhoneNumberIsValid_completeNumberWithSpecialSymbols_withSpaces_withoutPlus(){
        assertTrue(phoneNumberValidator.isValidPattern("55 (51) 99175 7318"));
    }

    @Test
    public void testIsValidPattern_shouldReturnTrue_whenPhoneNumberIsValid_completeNumberWithoutParentheses(){
        assertTrue(phoneNumberValidator.isValidPattern("+55 51 99175 7318"));
    }

    @Test
    public void testIsValidPattern_shouldReturnTrue_whenPhoneNumberIsValid_completeNumber_withSpacesBeforeAndAfterParentheses(){
        assertTrue(phoneNumberValidator.isValidPattern("+55 ( 51 ) 99175 7318"));
    }

    @Test
    public void testIsValidPattern_shouldThrowException_whenPhoneNumberIsInvalid_withoutCountryCode_withDDD(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            phoneNumberValidator.isValidPattern("(51) 99175-7318");
        });
        assertEquals("The pattern of the phone number is invalid!", thrown.getMessage());
    }




}
