package com.example.commentservice.service;

import com.example.commentservice.kafka.producer.CommentEventProducer;
import com.example.commentservice.misc.ErrorResponse;
import com.example.commentservice.model.Comment;
import com.example.commentservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentEventProducer commentEventProducer;

    public ResponseEntity<?> createComment(Comment comment){
        try {
            Comment savedComment = commentRepository.save(comment);
            commentEventProducer.sendCommentCreatedEvent(savedComment);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
        }
        catch(Exception e) {
            String errorMessage = "Error creating the comment";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(errorMessage));
        }
    }

    public Optional<Comment> getCommentById(String id){
        return commentRepository.findById(id);
    }

    public List<Comment> getCommentsByPostId(String postId){
        return commentRepository.findByPostId(postId);
    }

    public List<Comment> getCommentsByUserId(String userId){
        return commentRepository.findByUserId(userId);
    }

    public void deleteComment(String id){
        Optional<Comment> existingComment = commentRepository.findById(id);

        if(existingComment.isPresent()){
            commentRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Comment Not Found");
        }
    }

    public void deleteCommentsByUser(String userId){
        commentRepository.deleteByUserId(userId);
    }

    public void deleteCommentsByPost(String postId){
        commentRepository.deleteByPostId(postId);
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }
}
