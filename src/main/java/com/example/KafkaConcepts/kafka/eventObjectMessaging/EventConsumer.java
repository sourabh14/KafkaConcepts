package com.example.KafkaConcepts.kafka.eventObjectMessaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventConsumer {

    @KafkaListener(topics = "demoTopic2", groupId = "myConsumerGroup")
    public void consume(Event event) {
        log.info("Message received (kafka event consumer): {}", event);

    }

}
