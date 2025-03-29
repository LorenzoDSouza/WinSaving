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

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO body) {
        UserResponseDTO user = this.userService.createUser(body);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable UUID userId) {
        UserResponseDTO user = this.userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable UUID userId, @RequestBody UserRequestDTO body) {
        UserResponseDTO user = this.userService.updateUser(userId, body);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<UserResponseDTO> delete(@PathVariable UUID userId) {
        this.userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

