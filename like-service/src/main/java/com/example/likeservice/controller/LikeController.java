package com.example.likeservice.controller;

import com.example.likeservice.model.Like;
import com.example.likeservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;

    // POST Mapping to create a new comment
    @PostMapping
    public ResponseEntity<?> createLike(@RequestBody Like like){
        return likeService.createLike(like);
    }

    // GET Mapping to get a like by its id
    @GetMapping("/{id}")
    public Optional<Like> getLikeById(@PathVariable String id){
        return likeService.getLikeById(id);
    }

    // GET Mapping to get all likes on a specific post
    @GetMapping("/postId/{postId}")
    public List<Like> getLikeByPostId(@PathVariable String postId){
        return likeService.getLikesByPostId(postId);
    }

    // GET Mapping to get all likes by a specific user
    @GetMapping("/userId/{userId}")
    public List<Like> getLikeByUserId(@PathVariable String userId){
        return likeService.getLikesByUserId(userId);
    }

    // DELETE Mapping to delete a like by id
    @DeleteMapping("/{id}")
    public void deleteLike(@PathVariable String id){
        likeService.deleteLike(id);
    }

    // GET Mapping to get all the likes
    @GetMapping("")
    public List<Like> getAllLikes(){
        return likeService.getAllLikes();
    }
}
