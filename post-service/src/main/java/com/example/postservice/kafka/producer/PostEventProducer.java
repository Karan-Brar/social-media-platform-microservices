package com.example.postservice.kafka.producer;

import com.example.postservice.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostEventProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String POST_DELETED_TOPIC = "post-deleted";
    private static final String POST_CREATED_TOPIC = "post-created";

    public void sendPostDeletedEvent(String postId){
        kafkaTemplate.send(POST_DELETED_TOPIC, postId);
    }

    public void sendPostCreatedEvent(Post post){
        String message = post.getUserId() + ":" + post.getContent();
        kafkaTemplate.send(POST_CREATED_TOPIC, message);
    }
}
