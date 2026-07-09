package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.controller;

import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.request.UserRequestDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.ApiResponse;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.UserResponseDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAllUsers() {

        List<UserResponseDTO> users = userService.getAllUsers();

        return ResponseEntity.ok(
                ApiResponse.success("Users fetched successfully", users)
        );
    }

    @PostMapping("/create-admin")
    public ResponseEntity<ApiResponse<UserResponseDTO>> createAdmin(
            @Valid @RequestBody UserRequestDTO request) {

        UserResponseDTO admin = userService.createAdmin(request);

        return ResponseEntity.ok(
                ApiResponse.success("Admin created successfully", admin)
        );
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @PathVariable String username) {

        userService.deleteUser(username);

        return ResponseEntity.ok(
                ApiResponse.success("User deleted successfully", null)
        );
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUser(
            @PathVariable String username) {

        UserResponseDTO user = userService.getUserResponse(username);

        return ResponseEntity.ok(
                ApiResponse.success("User fetched successfully", user)
        );
    }
}
