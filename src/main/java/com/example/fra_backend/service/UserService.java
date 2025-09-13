package com.example.fra_backend.service;

import com.example.fra_backend.dto.AuthRequest;
import com.example.fra_backend.dto.AuthResponse;
import com.example.fra_backend.model.User;
import com.example.fra_backend.repository.UserRepository;
import com.example.fra_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtUtil jwtUtil;

    public User getUserByUsername(String username){
        return userRepo.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("No user with username: "+username));
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        System.out.println(request);
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        System.out.println("h2");

        if (authentication.isAuthenticated()) {
            System.out.println("Inside if block");
            User user = userRepo.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtUtil.generateToken(user.getUsername(), String.valueOf(user.getRole()));
            return new AuthResponse(token, user.getRole());
        } else {
            System.out.println("Inside else block");
            throw new RuntimeException("Invalid credentials");
        }
    }
}