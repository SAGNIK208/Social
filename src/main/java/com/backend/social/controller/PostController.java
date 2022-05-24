package com.backend.social.controller;

import com.backend.social.dto.PostDTO;
import com.backend.social.exceptions.InvalidPostException;
import com.backend.social.exceptions.UnauthorizedUserException;
import com.backend.social.models.PostEntity;
import com.backend.social.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(value ="/create-posts" , consumes = {"multipart/form-data"})
    public ResponseEntity createPost(@Valid @ModelAttribute PostDTO postDTO){
            postService.createPost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("New Post Created");
    }

    @GetMapping(value = "/all-posts")
    public ResponseEntity showPosts(){
        List<PostEntity> posts = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity deletePost(@PathVariable("id") String id){
        try {
            postService.deletePost(id);
        }
        catch (InvalidPostException p){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(p.getMessage());
        }
        catch (UnauthorizedUserException u){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(u.getMessage());
        }
        return  ResponseEntity.status(HttpStatus.OK).body("Post Deleted");
    }

    @PutMapping("/update-post/{id}")
    public ResponseEntity updatePost(@PathVariable("id") String id,@Valid @ModelAttribute PostDTO postDTO){
        try {
            postService.updatePost(postDTO,id);
        }
        catch (InvalidPostException p){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(p.getMessage());
        }
        catch (UnauthorizedUserException u){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(u.getMessage());
        }
        return  ResponseEntity.status(HttpStatus.OK).body("Post Updated");
    }
}
