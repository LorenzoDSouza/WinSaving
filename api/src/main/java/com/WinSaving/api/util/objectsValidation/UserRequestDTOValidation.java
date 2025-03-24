package com.WinSaving.api.util.objectsValidation;


import com.WinSaving.api.domain.user.UserRequestDTO;
import com.WinSaving.api.util.fieldsValidation.*;

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

    public boolean validate(UserRequestDTO userRequestDTO) {
        if (!emailValidator.validate(userRequestDTO.email())){
            throw new RuntimeException("Invalid email address!");
        }
        if (!phoneNumberValidator.validate(userRequestDTO.phoneNumber())){
            throw new RuntimeException("Invalid phone number!");
        }
        if (!firstNameValidator.validate(userRequestDTO.firstName())){
            throw new RuntimeException("Invalid first name!");
        }
        if (!lastNameValidator.validate(userRequestDTO.lastName())){
            throw new RuntimeException("Invalid last name!");
        }
        if (!passwordValidator.validate(userRequestDTO.password())){
            throw new RuntimeException("Invalid password!");
        }

        return true;
    }
}
