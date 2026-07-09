package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.service;

import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.request.UserRequestDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.UserResponseDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.entity.User;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.exception.UserNotFoundException;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.mapper.UserMapper;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDTO registerUser(UserRequestDTO dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists.");
        }

        User user = userMapper.toEntity(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(List.of("USER"));

        User savedUser = userRepository.save(user);

        log.info("New user registered: {}", savedUser.getUsername());

        return userMapper.toResponseDTO(savedUser);
    }

    @Transactional
    public UserResponseDTO createAdmin(UserRequestDTO dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists.");
        }

        User user = userMapper.toEntity(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(List.of("USER", "ADMIN"));

        User savedUser = userRepository.save(user);

        return userMapper.toResponseDTO(savedUser);
    }

    public User getUserByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found : " + username));
    }

    public UserResponseDTO getUserResponse(String username) {
        return userMapper.toResponseDTO(getUserByUsername(username));
    }

    public List<UserResponseDTO> getAllUsers() {
        return userMapper.toResponseDTOList(userRepository.findAll());
    }

    @Transactional
    public UserResponseDTO updateUser(String username, UserRequestDTO dto) {

        User user = getUserByUsername(username);

        if (dto.getUsername() != null && !dto.getUsername().isBlank()) {
            user.setUsername(dto.getUsername());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return userMapper.toResponseDTO(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(String username) {

        User user = getUserByUsername(username);

        userRepository.delete(user);

        log.info("User deleted : {}", username);
    }
}
