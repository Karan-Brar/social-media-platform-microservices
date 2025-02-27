package com.example.userservice.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String USER_DELETED_TOPIC = "user-deleted";

    public void sendUserDeletedEvent(String userId){
        kafkaTemplate.send(USER_DELETED_TOPIC, userId);
    }
}
