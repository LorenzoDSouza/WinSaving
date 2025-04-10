package com.WinSaving.api.controller;

import com.WinSaving.api.domain.user.User;
import com.WinSaving.api.domain.user.UserRequestDTO;
import com.WinSaving.api.domain.user.UserResponseDTO;
import com.WinSaving.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO body) {
        UserResponseDTO user = userService.createUser(body);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable UUID userId) {
        UserResponseDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable UUID userId, @RequestBody UserRequestDTO body) {
        UserResponseDTO user = userService.updateUser(userId, body);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<UserResponseDTO> delete(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/login")
    public ResponseEntity<UserResponseDTO> login (@PathVariable UUID userId, @RequestParam String password) {
        UserResponseDTO user = userService.authenticatePassword(userId, password);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}/first-name")
    public ResponseEntity<UserResponseDTO> updateFirstName(@PathVariable UUID userId, @RequestParam String firstName) {
        UserResponseDTO user = userService.updateFirstName(userId, firstName);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}/last-name")
    public ResponseEntity<UserResponseDTO> updateLastName(@PathVariable UUID userId, @RequestParam String lastName) {
        UserResponseDTO user = userService.updateLastName(userId, lastName);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}/email")
    public ResponseEntity<UserResponseDTO> updateEmail(@PathVariable UUID userId, @RequestParam String email) {
        UserResponseDTO user = userService.updateEmail(userId, email);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<UserResponseDTO> updatePassword(@PathVariable UUID userId, @RequestParam String oldPassword, @RequestParam String newPassword) {
        UserResponseDTO user = userService.updatePassword(userId, oldPassword, newPassword);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}/phone")
    public ResponseEntity<UserResponseDTO> updatePhoneNumber(@PathVariable UUID userId, @RequestParam String phone) {
        UserResponseDTO user = userService.updatePhoneNumber(userId, phone);
        return ResponseEntity.ok(user);
    }
}

