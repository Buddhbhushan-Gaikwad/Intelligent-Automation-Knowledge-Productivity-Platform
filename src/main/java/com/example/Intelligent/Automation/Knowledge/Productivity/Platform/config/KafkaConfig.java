package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String JOURNAL_TOPIC = "journal-events";

    @Bean
    public NewTopic journalTopic() {
        return new NewTopic(JOURNAL_TOPIC, 1, (short) 1);
    }
}
