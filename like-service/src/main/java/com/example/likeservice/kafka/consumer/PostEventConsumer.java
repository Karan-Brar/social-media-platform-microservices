package com.example.likeservice.kafka.consumer;

import com.example.likeservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PostEventConsumer {
    @Autowired
    private LikeService likeService;

    @KafkaListener(topics = "post-deleted", groupId = "like-service")
    public void handlePostDeleteEvent(String postId){
        likeService.deleteLikesByPost(postId);
    }
}
