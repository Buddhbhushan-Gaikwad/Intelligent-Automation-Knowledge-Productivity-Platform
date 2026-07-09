package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.service;

import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.JournalEntryResponseDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.entity.JournalEntry;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.mapper.JournalEntryMapper;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.repository.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserService userService;
    private final JournalEntryRepository journalEntryRepository;
    private final JournalEntryMapper journalEntryMapper;

    public List<JournalEntryResponseDTO> searchJournals(String username, String keyword) {

        Long userId = userService.getUserByUsername(username).getId();

        List<JournalEntry> entries =
                journalEntryRepository.searchByUserAndKeyword(userId, keyword);

        return journalEntryMapper.toResponseDTOList(entries);
    }
}
