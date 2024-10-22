package com.example.KafkaConcepts.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic stringTopic() {
        return TopicBuilder
                .name("demoTopic")
                .build();
    }

    @Bean
    public NewTopic eventTopic() {
        return TopicBuilder
                .name("demoTopic2")
                .build();
    }

}
