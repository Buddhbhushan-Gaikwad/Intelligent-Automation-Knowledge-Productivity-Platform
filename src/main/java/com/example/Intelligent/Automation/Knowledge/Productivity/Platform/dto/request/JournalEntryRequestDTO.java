package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JournalEntryRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(max = 100)
    private String title;

    @NotBlank(message = "Content is required")
    private String content;
}
