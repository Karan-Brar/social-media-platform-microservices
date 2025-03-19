package com.example.likeservice.kafka.producer;


import com.example.likeservice.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LikeEventProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String LIKE_POSTED_TOPIC = "like-posted";

    public void sendLikePostedTopic(Like like){
        String message = like.getPostId() + ":" + like.getUserId();
        kafkaTemplate.send(LIKE_POSTED_TOPIC, message);
    }
}
