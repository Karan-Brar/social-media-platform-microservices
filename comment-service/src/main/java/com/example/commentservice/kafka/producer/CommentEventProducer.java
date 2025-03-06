package com.example.commentservice.kafka.producer;

import com.example.commentservice.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommentEventProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String COMMENT_CREATED_TOPIC = "comment-created";

    public void sendCommentCreatedEvent(Comment comment){
        String message = comment.getPostId() + ":" + comment.getUserId() + ":" + comment.getContent();
        kafkaTemplate.send(COMMENT_CREATED_TOPIC, message);
    }
}
