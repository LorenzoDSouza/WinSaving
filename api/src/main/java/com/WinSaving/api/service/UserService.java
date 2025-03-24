package com.WinSaving.api.service;

import com.WinSaving.api.domain.user.User;
import com.WinSaving.api.domain.user.UserRequestDTO;
import com.WinSaving.api.domain.user.UserResponseDTO;
import com.WinSaving.api.repositories.UserRepository;
import com.WinSaving.api.util.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Validator<String> emailValidator;
    private final Validator<String> phoneNumberValidator;
    private final Validator<String> firstNameValidator;
    private final Validator<String> lastNameValidator;
    private final Validator<String> passwordValidator;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        emailValidator = new EmailValidator();
        phoneNumberValidator = new PhoneNumberValidator();
        firstNameValidator = new FirstNameValidator();
        lastNameValidator = new LastNameValidator();
        passwordValidator = new PasswordValidator();
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {
        if (!emailValidator.validate(dto.email())){
            throw new RuntimeException("Invalid email address!");
        }
        if (!phoneNumberValidator.validate(dto.phoneNumber())){
            throw new RuntimeException("Invalid phone number!");
        }
        if (!firstNameValidator.validate(dto.firstName())){
            throw new RuntimeException("Invalid first name!");
        }
        if (!lastNameValidator.validate(dto.lastName())){
            throw new RuntimeException("Invalid last name!");
        }
        if (!passwordValidator.validate(dto.password())){
            throw new RuntimeException("Invalid password!");
        }


        return null;
    }

}
