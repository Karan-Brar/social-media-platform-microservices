package com.example.likeservice.service;

import com.example.likeservice.kafka.producer.LikeEventProducer;
import com.example.likeservice.misc.ErrorResponse;
import com.example.likeservice.model.Like;
import com.example.likeservice.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private LikeEventProducer likeEventProducer;

    public ResponseEntity<?> createLike(Like like){
        try{
            Like savedLike = likeRepository.save(like);
            likeEventProducer.sendLikePostedTopic(savedLike);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLike);
        }
        catch(Exception e){
            String errorMessage = "Issues liking the post";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(errorMessage));
        }
    }

    public Optional<Like> getLikeById(String id){
        return likeRepository.findById(id);
    }

    public List<Like> getLikesByPostId(String postId){
        return likeRepository.findByPostId(postId);
    }

    public List<Like> getLikesByUserId(String userId){
        return likeRepository.findByUserId(userId);
    }

    public void deleteLike(String id){
        Optional<Like> existingLike = likeRepository.findById(id);

        if(existingLike.isPresent()){
            likeRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Comment Not Found");
        }
    }

    public void deleteLikesByUser(String userId){
        likeRepository.deleteByUserId(userId);
    }

    public void deleteLikesByPost(String postId){
        likeRepository.deleteByPostId(postId);
    }

    public List<Like> getAllLikes(){
        return likeRepository.findAll();
    }
}
