package com.example.commentservice.repository;

import com.example.commentservice.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByPostId(String postId); // Find all comments for a post
    List<Comment> findByUserId(String userId); // Find all comments made by a specific user on different posts
    void deleteByUserId(String userId);
    void deleteByPostId(String postid);
}
