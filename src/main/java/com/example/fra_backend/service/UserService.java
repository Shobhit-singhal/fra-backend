package com.example.fra_backend.service;

import com.example.fra_backend.dto.UserDTO;
import com.example.fra_backend.mapper.UserMapper;
import com.example.fra_backend.model.User;
import com.example.fra_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO saveUser(UserDTO dto) {
        User user = UserMapper.toEntity(dto);
        User saved = userRepository.save(user);
        return UserMapper.toDTO(saved);
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDTO);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(UserMapper::toDTO);
    }

    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(UserMapper::toDTO);
    }

    public Optional<UserDTO> getUserByPhone(String phone) {
        return userRepository.findByPhone(phone).map(UserMapper::toDTO);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
