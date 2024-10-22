package com.example.KafkaConcepts.kafka.eventObjectMessaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventProducer {
    private final KafkaTemplate<String, Event> kafkaTemplate;
    // We will use json serializer and deserializer for event

    public void sendMessage(Event event) {
        log.info("Sending event to kafka: {}", event);
        Message<Event> eventMessage = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "demoTopic2")
                .build();

        kafkaTemplate.send(eventMessage);
    }
}
