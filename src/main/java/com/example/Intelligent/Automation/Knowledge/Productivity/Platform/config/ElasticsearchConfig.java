package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(
        basePackages = "com.example.Intelligent.Automation.Knowledge.Productivity.Platform.repository"
)
public class ElasticsearchConfig {
}
