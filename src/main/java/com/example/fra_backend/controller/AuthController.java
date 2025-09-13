package com.example.fra_backend.controller;

import com.example.fra_backend.dto.AuthRequest;
import com.example.fra_backend.dto.AuthResponse;
import com.example.fra_backend.model.User;
import com.example.fra_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return userService.login(request);
    }
}