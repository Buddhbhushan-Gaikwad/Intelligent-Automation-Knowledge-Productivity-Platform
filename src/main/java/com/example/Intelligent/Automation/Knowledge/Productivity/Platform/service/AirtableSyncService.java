package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirtableSyncService {

    private final UserService userService;

    @Scheduled(fixedRate = 3600000)
    public void syncUsers() {

        // Future integration with Airtable REST API

        log.info("Airtable Sync Started");

        userService.getAllUsers()
                .forEach(user ->
                        log.info("Syncing user : {}", user.getUsername()));

        log.info("Airtable Sync Completed");
    }
}
