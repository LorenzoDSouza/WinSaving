package com.WinSaving.api.util.objectsValidation;


import com.WinSaving.api.domain.user.UserRequestDTO;
import com.WinSaving.api.util.fieldsValidation.*;
import org.springframework.stereotype.Component;

@Component
public class UserRequestDTOValidation {
    private final Validator<String> emailValidator;
    private final Validator<String> phoneNumberValidator;
    private final Validator<String> firstNameValidator;
    private final Validator<String> lastNameValidator;
    private final Validator<String> passwordValidator;

    public UserRequestDTOValidation() {
        emailValidator = new EmailValidator();
        phoneNumberValidator = new PhoneNumberValidator();
        firstNameValidator = new FirstNameValidator();
        lastNameValidator = new LastNameValidator();
        passwordValidator = new PasswordValidator();
    }

    public void validate(UserRequestDTO userRequestDTO) {
        if (!emailValidator.validate(userRequestDTO.email())){
            throw new IllegalArgumentException("Invalid email address!");
        }
        if (!phoneNumberValidator.validate(userRequestDTO.phoneNumber())){
            throw new IllegalArgumentException("Invalid phone number!");
        }
        if (!firstNameValidator.validate(userRequestDTO.firstName())){
            throw new IllegalArgumentException("Invalid first name!");
        }
        if (!lastNameValidator.validate(userRequestDTO.lastName())){
            throw new IllegalArgumentException("Invalid last name!");
        }
        if (!passwordValidator.validate(userRequestDTO.password())){
            throw new IllegalArgumentException("Invalid password!");
        }
    }
}
