package com.WinSaving.api.service;

import com.WinSaving.api.domain.user.User;
import com.WinSaving.api.domain.user.UserRequestDTO;
import com.WinSaving.api.domain.user.UserResponseDTO;
import com.WinSaving.api.repositories.UserRepository;
import com.WinSaving.api.util.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Validator<String> emailValidator;

    public UserService(UserRepository userRepository, Validator<String> emailValidator) {
        this.userRepository = userRepository;
        this.emailValidator = emailValidator;
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = new User();

        return null;
    }

}
