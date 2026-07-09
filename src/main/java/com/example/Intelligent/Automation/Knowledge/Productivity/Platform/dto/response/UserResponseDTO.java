package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;

    private String username;

    private List<String> roles;

    private int journalCount;

    private LocalDateTime createdAt;
}
