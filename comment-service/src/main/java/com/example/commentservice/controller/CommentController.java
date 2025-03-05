package com.example.commentservice.controller;

import com.example.commentservice.model.Comment;
import com.example.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // POST Mapping to create a new comment
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody Comment comment){
        return commentService.createComment(comment);
    }

    // GET Mapping to get a comment by its ID
    @GetMapping("/{id}")
    public Optional<Comment> getCommentById(@PathVariable String id){
        return commentService.getCommentById(id);
    }

    // GET Mapping to get all the comments made by a specific user ID
    @GetMapping("/userId/{userId}")
    public List<Comment> getCommentsByUserId(@PathVariable String userId){
        return commentService.getCommentsByUserId(userId);
    }

    // GET Mapping to get all the comments with a specific post ID
    @GetMapping("/postId/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable String postId){
        return commentService.getCommentsByPostId(postId);
    }

    @DeleteMapping("/{id}")
    public void  deleteComment(@PathVariable String id){
        commentService.deleteComment(id);
    }

    @GetMapping("")
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

}
