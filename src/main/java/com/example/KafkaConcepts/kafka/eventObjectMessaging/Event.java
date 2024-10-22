package com.example.KafkaConcepts.kafka.eventObjectMessaging;

import lombok.Data;

@Data
public class Event {
    private int id;
    private String name;
    private boolean flag;
}
