package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JournalEntryResponseDTO {

    private Long id;

    private String title;

    private String content;

    private String authorUsername;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
