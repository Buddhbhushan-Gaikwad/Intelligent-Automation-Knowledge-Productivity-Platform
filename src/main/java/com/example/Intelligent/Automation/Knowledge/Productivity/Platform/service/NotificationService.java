package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    public void sendNotification(String username, String message) {

        // Future:
        // Email
        // SMS
        // Push Notification

        log.info("Notification sent to {} : {}", username, message);
    }
}
