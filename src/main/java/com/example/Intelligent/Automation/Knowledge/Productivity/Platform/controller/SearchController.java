package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.controller;

import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.ApiResponse;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.JournalEntryResponseDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<JournalEntryResponseDTO>>> searchJournals(

            @RequestParam("query") String query,

            Authentication authentication) {

        List<JournalEntryResponseDTO> journals =
                searchService.searchJournals(
                        authentication.getName(),
                        query
                );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Search completed successfully",
                        journals
                )
        );
    }
}
