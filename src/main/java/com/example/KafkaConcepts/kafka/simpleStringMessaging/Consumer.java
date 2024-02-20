package com.example.KafkaConcepts.kafka.simpleStringMessaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {

    @KafkaListener(topics = "demoTopic", groupId = "myConsumerGroup")
    public void consume(String message) {
        log.info("Message received(kafka consumer): {}", message);
    }

}
