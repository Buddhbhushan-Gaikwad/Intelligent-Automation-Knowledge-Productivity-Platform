package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.event;

import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.config.KafkaConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "app.kafka",
        name = "enabled",
        havingValue = "false"
)
public class JournalEventProducer {

    private final KafkaTemplate<String, JournalEvent> kafkaTemplate;

    public void publish(JournalEvent event) {

        kafkaTemplate.send(KafkaConfig.JOURNAL_TOPIC, event);

        log.info("Journal Event Published : {}", event);
    }
}