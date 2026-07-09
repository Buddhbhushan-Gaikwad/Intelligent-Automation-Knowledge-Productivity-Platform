package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.event;

import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.config.KafkaConfig;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "app.kafka",
        name = "enabled",
        havingValue = "false"
)
public class NotificationConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = KafkaConfig.JOURNAL_TOPIC,
            groupId = "journal-group"
    )
    public void consume(JournalEvent event) {

        log.info("Received Event : {}", event);

        String message = switch (event.getAction()) {

            case "CREATED" ->
                    "Journal '" + event.getTitle() + "' created successfully.";

            case "UPDATED" ->
                    "Journal '" + event.getTitle() + "' updated successfully.";

            case "DELETED" ->
                    "Journal '" + event.getTitle() + "' deleted successfully.";

            default ->
                    "Journal event received.";
        };

        notificationService.sendNotification(
                event.getUsername(),
                message
        );
    }
}