package com.example.commentservice.kafka.consumer;

import com.example.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostEventConsumer {
    @Autowired
    private CommentService commentService;

    @KafkaListener(topics = "post-deleted", groupId = "comment-service")
    public void handlePostDeleteEvent(String postId){
        commentService.deleteCommentsByPost(postId);
    }
}
