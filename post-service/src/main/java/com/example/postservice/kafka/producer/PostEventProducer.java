package com.example.postservice.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostEventProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String POST_DELTED_TOPIC = "post-deleted";

    public void sendPostDeletedEvent(String postId){
        kafkaTemplate.send(POST_DELTED_TOPIC, postId);
    }
}
