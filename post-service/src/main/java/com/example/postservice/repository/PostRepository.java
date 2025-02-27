package com.example.postservice.repository;

import com.example.postservice.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByUserId(String userId); // Find all the posts by a specific user
    void deleteByUserId(String userId); // Deletes all posts by a specific user
}
