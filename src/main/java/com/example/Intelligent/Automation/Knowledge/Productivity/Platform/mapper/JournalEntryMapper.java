package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.mapper;

import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.request.JournalEntryRequestDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.JournalEntryResponseDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.entity.JournalEntry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalEntryMapper {

    public JournalEntry toEntity(JournalEntryRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        JournalEntry entry = new JournalEntry();

        entry.setTitle(dto.getTitle());
        entry.setContent(dto.getContent());

        return entry;
    }

    public JournalEntryResponseDTO toResponseDTO(JournalEntry entry) {

        if (entry == null) {
            return null;
        }

        JournalEntryResponseDTO dto = new JournalEntryResponseDTO();

        dto.setId(entry.getId());
        dto.setTitle(entry.getTitle());
        dto.setContent(entry.getContent());

        if (entry.getUser() != null) {
            dto.setAuthorUsername(entry.getUser().getUsername());
        }

        dto.setCreatedAt(entry.getCreatedAt());
        dto.setUpdatedAt(entry.getUpdatedAt());

        return dto;
    }

    public List<JournalEntryResponseDTO> toResponseDTOList(List<JournalEntry> entries) {

        return entries.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
