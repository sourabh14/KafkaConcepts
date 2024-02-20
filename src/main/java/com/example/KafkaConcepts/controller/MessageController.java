package com.example.KafkaConcepts.controller;

import com.example.KafkaConcepts.kafka.eventObjectMessaging.Event;
import com.example.KafkaConcepts.kafka.eventObjectMessaging.EventProducer;
import com.example.KafkaConcepts.kafka.simpleStringMessaging.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
public class MessageController {
    private final Producer kafkaProducer;
    private final EventProducer kafkaEventProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent");
    }

    @GetMapping("/publishEvent")
    public ResponseEntity<String> publishJson(@RequestBody Event event) {
        kafkaEventProducer.sendMessage(event);
        return ResponseEntity.ok("Message sent");
    }

}
