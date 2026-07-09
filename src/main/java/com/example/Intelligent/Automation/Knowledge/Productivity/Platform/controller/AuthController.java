package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.controller;

import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.request.AuthRequestDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.request.UserRequestDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.ApiResponse;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.UserResponseDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.security.JwtUtil;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> register(
            @Valid @RequestBody UserRequestDTO request) {

        UserResponseDTO response = userService.registerUser(request);

        return ResponseEntity.ok(
                ApiResponse.success("User registered successfully", response)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(
            @Valid @RequestBody AuthRequestDTO request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(
                ApiResponse.success("Login successful", token)
        );
    }
}
