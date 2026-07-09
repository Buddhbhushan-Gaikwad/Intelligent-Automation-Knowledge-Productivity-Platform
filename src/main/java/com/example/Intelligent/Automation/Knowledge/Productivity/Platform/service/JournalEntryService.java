package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.service;

import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.request.JournalEntryRequestDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.JournalEntryResponseDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.entity.JournalEntry;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.entity.User;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.exception.JournalNotFoundException;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.mapper.JournalEntryMapper;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.repository.JournalEntryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final JournalEntryMapper journalEntryMapper;
    private final UserService userService;

    @Transactional
    public JournalEntryResponseDTO createEntry(
            JournalEntryRequestDTO dto,
            String username) {

        User user = userService.getUserByUsername(username);

        JournalEntry entry = journalEntryMapper.toEntity(dto);

        entry.setUser(user);

        JournalEntry savedEntry = journalEntryRepository.save(entry);

        log.info("Journal created by {}", username);

        return journalEntryMapper.toResponseDTO(savedEntry);
    }

    public Page<JournalEntryResponseDTO> getUserEntries(
            String username,
            int page,
            int size) {

        User user = userService.getUserByUsername(username);

        Pageable pageable =
                PageRequest.of(page, size,
                        Sort.by("createdAt").descending());

        return journalEntryRepository
                .findByUserId(user.getId(), pageable)
                .map(journalEntryMapper::toResponseDTO);
    }

    public JournalEntryResponseDTO getEntry(
            Long id,
            String username) {

        JournalEntry entry = journalEntryRepository.findById(id)
                .orElseThrow(() ->
                        new JournalNotFoundException("Journal not found."));

        if (!entry.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("Access denied.");
        }

        return journalEntryMapper.toResponseDTO(entry);
    }

    @Transactional
    public JournalEntryResponseDTO updateEntry(
            Long id,
            JournalEntryRequestDTO dto,
            String username) {

        JournalEntry entry = journalEntryRepository.findById(id)
                .orElseThrow(() ->
                        new JournalNotFoundException("Journal not found."));

        if (!entry.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("Access denied.");
        }

        if (dto.getTitle() != null && !dto.getTitle().isBlank()) {
            entry.setTitle(dto.getTitle());
        }

        if (dto.getContent() != null && !dto.getContent().isBlank()) {
            entry.setContent(dto.getContent());
        }

        return journalEntryMapper.toResponseDTO(
                journalEntryRepository.save(entry));
    }

    @Transactional
    public void deleteEntry(
            Long id,
            String username) {

        JournalEntry entry = journalEntryRepository.findById(id)
                .orElseThrow(() ->
                        new JournalNotFoundException("Journal not found."));

        if (!entry.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("Access denied.");
        }

        journalEntryRepository.delete(entry);

        log.info("Journal deleted : {}", id);
    }

    public List<JournalEntryResponseDTO> searchEntries(
            String username,
            String keyword) {

        User user = userService.getUserByUsername(username);

        return journalEntryMapper.toResponseDTOList(
                journalEntryRepository.searchByUserAndKeyword(
                        user.getId(),
                        keyword));
    }
}
