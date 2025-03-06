package com.example.commentservice.kafka.consumer;

import com.example.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class UserEventConsumer {
    @Autowired
    private CommentService commentService;


    @KafkaListener(topics = "user-deleted", groupId = "comment-service")
    public void handleUserDeleteEvent(String userId) {
        commentService.deleteCommentsByUser(userId);
    }
}
