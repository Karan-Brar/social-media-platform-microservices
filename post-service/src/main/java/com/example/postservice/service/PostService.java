package com.example.postservice.service;

import com.example.postservice.misc.ErrorResponse;
import com.example.postservice.model.Post;
import com.example.postservice.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public ResponseEntity<?> createPost(Post post){
        try {
            Post savedPost = postRepository.save(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
        } catch (Exception e) {
            String errorMessage = "Error creating the post";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(errorMessage));
        }
    }

    public Optional<Post> getPostById(String id){
        return postRepository.findById(id);
    }

    public List<Post> getPostsByUserId(String userId){
        return postRepository.findByUserId(userId);
    }

    public ResponseEntity<?> updatePostContent(String id, String newContent){
        Optional<Post> existingPost = postRepository.findById(id);

        if(existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setContent(newContent);
            return ResponseEntity.status(HttpStatus.OK).body(postRepository.save(post));
        } else {
            String errorMessage = "Post not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errorMessage));
        }
    }

    public void deletePost(String id){
        Optional<Post> existingUser = postRepository.findById(id);

        if(existingUser.isPresent()){
            postRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Post Not Found");
        }
    }

    public void deletePostsByUser(String userId){
        postRepository.deleteByUserId(userId);
    }


    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
}
