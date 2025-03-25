package com.WinSaving.api.service;

import com.WinSaving.api.domain.monthlyBudget.MonthlyBudget;
import com.WinSaving.api.domain.user.User;
import com.WinSaving.api.domain.user.UserRequestDTO;
import com.WinSaving.api.domain.user.UserResponseDTO;
import com.WinSaving.api.exceptions.UserCreationException;
import com.WinSaving.api.repositories.UserRepository;
import com.WinSaving.api.util.objectsValidation.UserRequestDTOValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRequestDTOValidation userRequestDTOValidation;

    @Autowired
    public UserService(
            UserRepository userRepository,
            UserRequestDTOValidation userRequestDTOValidation,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRequestDTOValidation = userRequestDTOValidation;
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {
        try {
            userRequestDTOValidation.validate(dto);

            User user = new User();
            user.setEmail(dto.email());
            user.setPhoneNumber(dto.phoneNumber());
            user.setFirstName(dto.firstName());
            user.setLastName(dto.lastName());
            user.setPassword(passwordEncoder.encode(dto.password()));

            user.setMonthlyBudget(new MonthlyBudget());
            user = userRepository.save(user);

            return new UserResponseDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());

        } catch (IllegalArgumentException e) {
            throw new UserCreationException("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            throw new UserCreationException("Error while saving user: " + e.getMessage());
        }
    }



}
