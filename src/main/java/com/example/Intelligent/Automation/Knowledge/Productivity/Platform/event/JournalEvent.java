package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalEvent implements Serializable {

    private Long journalId;

    private String username;

    private String action; // CREATED, UPDATED, DELETED

    private String title;

    private LocalDateTime timestamp;
}
