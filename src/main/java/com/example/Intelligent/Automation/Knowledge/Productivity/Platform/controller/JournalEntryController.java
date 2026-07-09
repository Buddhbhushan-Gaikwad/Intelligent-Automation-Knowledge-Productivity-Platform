package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.controller;

import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.request.JournalEntryRequestDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.ApiResponse;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.JournalEntryResponseDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.service.JournalEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/journals")
@RequiredArgsConstructor
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    @PostMapping
    public ResponseEntity<ApiResponse<JournalEntryResponseDTO>> createJournal(
            @Valid @RequestBody JournalEntryRequestDTO request,
            Authentication authentication) {

        JournalEntryResponseDTO response =
                journalEntryService.createEntry(
                        request,
                        authentication.getName());

        return ResponseEntity.ok(
                ApiResponse.success("Journal created successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<JournalEntryResponseDTO>>> getAllJournals(

            Authentication authentication,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size) {

        Page<JournalEntryResponseDTO> journals =
                journalEntryService.getUserEntries(
                        authentication.getName(),
                        page,
                        size);

        return ResponseEntity.ok(
                ApiResponse.success("Journal fetched successfully", journals));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JournalEntryResponseDTO>> getJournal(

            @PathVariable Long id,

            Authentication authentication) {

        JournalEntryResponseDTO journal =
                journalEntryService.getEntry(
                        id,
                        authentication.getName());

        return ResponseEntity.ok(
                ApiResponse.success("Journal fetched successfully", journal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<JournalEntryResponseDTO>> updateJournal(

            @PathVariable Long id,

            @Valid @RequestBody JournalEntryRequestDTO request,

            Authentication authentication) {

        JournalEntryResponseDTO response =
                journalEntryService.updateEntry(
                        id,
                        request,
                        authentication.getName());

        return ResponseEntity.ok(
                ApiResponse.success("Journal updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteJournal(

            @PathVariable Long id,

            Authentication authentication) {

        journalEntryService.deleteEntry(
                id,
                authentication.getName());

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Journal deleted successfully",
                        null));
    }
}
