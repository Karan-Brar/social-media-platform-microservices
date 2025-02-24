package com.example.postservice.controller;

import com.example.postservice.model.Post;
import com.example.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    // POST Mapping to create a new post
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    // GET Mapping to get a post by its ID
    @GetMapping("/id/{id}")
    public Optional<Post> getPostById(@PathVariable String id){
        return postService.getPostById(id);
    }

    // GET Mapping to get a post by the id of the user who made the post
    @GetMapping("/userId/{userid}")
    public List<Post> getPostsByUserid(@PathVariable String userId){
        return postService.getPostsByUserId(userId);
    }

    // PATCH mapping to update the content of the post
    @PatchMapping("{id}/content")
    public ResponseEntity<?> updatePostContent(@PathVariable String id, @RequestBody Map<String, String> request){
        String newPostContent = request.get("updatedContent");
        return postService.updatePostContent(id, newPostContent);
    }

    // DELETE endpoint to delete an existing post
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        postService.deletePost(id);
    }

    // GET Mapping to get all the posts
    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }
}
