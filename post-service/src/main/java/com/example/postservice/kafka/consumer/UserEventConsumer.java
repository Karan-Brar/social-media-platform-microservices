package com.example.postservice.kafka.consumer;

import com.example.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumer {
    @Autowired
    private PostService postService;

    @KafkaListener(topics = "user-deleted", groupId = "post-service")
    public void handleUserDeleteEvent(String userId) {
        postService.deletePostsByUser(userId);
    }
}
