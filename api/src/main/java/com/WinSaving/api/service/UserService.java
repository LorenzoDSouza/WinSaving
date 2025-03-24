package com.WinSaving.api.service;

import com.WinSaving.api.domain.monthlyBudget.MonthlyBudget;
import com.WinSaving.api.domain.user.User;
import com.WinSaving.api.domain.user.UserRequestDTO;
import com.WinSaving.api.domain.user.UserResponseDTO;
import com.WinSaving.api.repositories.UserRepository;
import com.WinSaving.api.util.objectsValidation.UserRequestDTOValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRequestDTOValidation userRequestDTOValidation;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        userRequestDTOValidation = new UserRequestDTOValidation();
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {
        if(!userRequestDTOValidation.validate(dto)){
            throw new RuntimeException("There was a problem while creating the user!");
        }

        User user = new User();

        user.setEmail(dto.email());
        user.setPhoneNumber(dto.phoneNumber());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setPassword(passwordEncoder.encode(dto.password()));

        user.setMonthlyBudget(new MonthlyBudget());

        try {
            user = userRepository.save(user);
            return new UserResponseDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
        } catch (Exception e) {
            throw new RuntimeException("Error while saving user: " + e.getMessage());
        }
    }

}
