package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.controller;

import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.request.UserRequestDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.ApiResponse;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.UserResponseDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getCurrentUser(
            Authentication authentication) {

        UserResponseDTO user =
                userService.getUserResponse(authentication.getName());

        return ResponseEntity.ok(
                ApiResponse.success("User fetched successfully", user)
        );
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateCurrentUser(
            Authentication authentication,
            @Valid @RequestBody UserRequestDTO request) {

        UserResponseDTO updatedUser =
                userService.updateUser(
                        authentication.getName(),
                        request
                );

        return ResponseEntity.ok(
                ApiResponse.success("User updated successfully", updatedUser)
        );
    }

    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<Void>> deleteCurrentUser(
            Authentication authentication) {

        userService.deleteUser(authentication.getName());

        return ResponseEntity.ok(
                ApiResponse.success("User deleted successfully", null)
        );
    }
}
