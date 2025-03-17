package com.example.likeservice.repository;

import com.example.likeservice.model.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends MongoRepository<Like, String> {
    List<Like> findByPostId(String postId); // Find all comments for a post
    List<Like> findByUserId(String userId); // Find all comments made by a specific user on different posts
    void deleteByUserId(String userId);
    void deleteByPostId(String postid);
}
