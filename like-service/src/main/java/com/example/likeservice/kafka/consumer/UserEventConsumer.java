package com.example.likeservice.kafka.consumer;

import com.example.likeservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumer {
    @Autowired
    private LikeService likeService;

    @KafkaListener(topics = "user-deleted", groupId = "like-service")
    public void handleUserDeleteEvent(String userId){
        likeService.deleteLikesByUser(userId);
    }
}
