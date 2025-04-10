package com.WinSaving.api.service;

import com.WinSaving.api.domain.monthlyBudget.MonthlyBudget;
import com.WinSaving.api.domain.savingGoal.SavingGoal;
import com.WinSaving.api.domain.user.User;
import com.WinSaving.api.domain.user.UserRequestDTO;
import com.WinSaving.api.domain.user.UserResponseDTO;
import com.WinSaving.api.exceptions.IncorrectPasswordException;
import com.WinSaving.api.exceptions.UserCreationException;
import com.WinSaving.api.exceptions.UserNotFoundException;
import com.WinSaving.api.exceptions.UserUpdateException;
import com.WinSaving.api.repositories.MonthlyBudgetRepository;
import com.WinSaving.api.repositories.SavingGoalRepository;
import com.WinSaving.api.repositories.UserRepository;
import com.WinSaving.api.util.fieldsValidation.userFields.*;
import com.WinSaving.api.util.objectsValidation.UserRequestDTOValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRequestDTOValidation userRequestDTOValidation;
    private final SavingGoalRepository savingGoalRepository;
    private final MonthlyBudgetRepository monthlyBudgetRepository;
    private final FirstNameValidator firstNameValidator;
    private final LastNameValidator lastNameValidator;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final PhoneNumberValidator phoneNumberValidator;

    @Autowired
    public UserService(
            UserRepository userRepository,
            UserRequestDTOValidation userRequestDTOValidation,
            PasswordEncoder passwordEncoder,
            SavingGoalRepository savingGoalRepository,
            MonthlyBudgetRepository monthlyBudgetRepository,
            FirstNameValidator firstNameValidator,
            LastNameValidator lastNameValidator, EmailValidator emailValidator, PasswordValidator passwordValidator, PhoneNumberValidator phoneNumberValidator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRequestDTOValidation = userRequestDTOValidation;
        this.savingGoalRepository = savingGoalRepository;
        this.monthlyBudgetRepository = monthlyBudgetRepository;
        this.firstNameValidator = firstNameValidator;
        this.lastNameValidator = lastNameValidator;
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
        this.phoneNumberValidator = phoneNumberValidator;
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

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(UUID id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        System.out.println("User found with id: " + id);
        return new UserResponseDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
    }

    @Transactional
    public UserResponseDTO updateUser(UUID userId, UserRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        if (dto.email() != null) user.setEmail(dto.email());
        if (dto.phoneNumber() != null) user.setPhoneNumber(dto.phoneNumber());
        if (dto.firstName() != null) user.setFirstName(dto.firstName());
        if (dto.lastName() != null) user.setLastName(dto.lastName());
        if (dto.password() != null) user.setPassword(passwordEncoder.encode(dto.password()));

        user = userRepository.save(user);
        return new UserResponseDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
    }

    @Transactional
    public UserResponseDTO updateFirstName(UUID userId, String firstName) {
        if(!firstNameValidator.validate(firstName)){
            throw new UserUpdateException("Invalid first name!");
        }

        UserRequestDTO dto = new UserRequestDTO(firstName, null, null, null, null);

        return updateUser(userId, dto);
    }

    @Transactional
    public UserResponseDTO updateLastName(UUID userId, String lastName) {
        if(!lastNameValidator.validate(lastName)){
            throw new UserUpdateException("Invalid last name!");
        }

        UserRequestDTO dto = new UserRequestDTO(null, lastName, null, null, null);

        return updateUser(userId, dto);
    }

    @Transactional
    public UserResponseDTO updateEmail(UUID userId, String email) {
        if(!emailValidator.validate(email)){
            throw new UserUpdateException("Invalid email!");
        }

        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent() && !existingUser.get().getId().equals(userId)) {
            throw new UserUpdateException("Email is already in use by another user.");
        }

        UserRequestDTO dto = new UserRequestDTO(null, null, email, null, null);

        return updateUser(userId, dto);
    }

    @Transactional
    public UserResponseDTO updatePassword(UUID userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IncorrectPasswordException("Old password is incorrect.");
        }

        if (!passwordValidator.validate(newPassword)) {
            throw new UserUpdateException("New password is invalid.");
        }

        UserRequestDTO dto = new UserRequestDTO(null, null, null, newPassword, null);

        return updateUser(userId, dto);
    }

    @Transactional
    public UserResponseDTO updatePhoneNumber(UUID userId, String newPhoneNumber) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        if (newPhoneNumber.equals(user.getPhoneNumber())) {
            throw new UserUpdateException("Old phone number is the same as the new phone number!.");
        }

        if (!phoneNumberValidator.validate(newPhoneNumber)) {
            throw new UserUpdateException("New phone number is invalid.");
        }

        UserRequestDTO dto = new UserRequestDTO(null, null, null, null, newPhoneNumber);

        return updateUser(userId, dto);
    }


    @Transactional
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        savingGoalRepository.deleteAll(user.getGoals());
        monthlyBudgetRepository.deleteById(user.getMonthlyBudget().getId());

        userRepository.deleteById(userId);
    }

    @Transactional
    public UserResponseDTO authenticatePassword(UUID userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectPasswordException("The password is incorrect");
        }

        System.out.println("Logged successfully!");

        return new UserResponseDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
    }


}
