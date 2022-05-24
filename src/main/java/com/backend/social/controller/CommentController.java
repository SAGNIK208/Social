package com.backend.social.controller;

import com.backend.social.dto.CommentDTO;
import com.backend.social.dto.PostDTO;
import com.backend.social.exceptions.InvalidCommentException;
import com.backend.social.exceptions.InvalidPostException;
import com.backend.social.exceptions.UnauthorizedUserException;
import com.backend.social.models.CommentEntity;
import com.backend.social.models.PostEntity;
import com.backend.social.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts/{id}")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping(value = "/create-comments" , consumes = {"multipart/form-data"})
    public ResponseEntity createComment(@Valid @ModelAttribute CommentDTO commentDTO,@PathVariable("id") String postId){
        try {
            commentService.createComment(commentDTO,postId);
        } catch (UsernameNotFoundException u) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(u.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("New comment added");
    }

    @DeleteMapping(value = "/delete-comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("id") String postId,@PathVariable("commentId") String commentId){
        try {
            commentService.deleteComment(postId,commentId);
        }
        catch (InvalidCommentException c){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(c.getMessage());
        }
        catch (InvalidPostException i){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(i.getMessage());
        }
        catch (UnauthorizedUserException u){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(u.getMessage());
        }
        return  ResponseEntity.status(HttpStatus.OK).body("Comment Deleted");
    }

    @PutMapping("/update-comment/{commentId}")
    public ResponseEntity updatePost(@PathVariable("id") String postId,@PathVariable("commentId") String commentId,@Valid @ModelAttribute CommentDTO commentDTO){
        try {
            commentService.updateComment(postId, commentId, commentDTO);
        }
        catch (InvalidCommentException c){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(c.getMessage());
        }
        catch (InvalidPostException p){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(p.getMessage());
        }
        catch (UnauthorizedUserException u){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(u.getMessage());
        }
        return  ResponseEntity.status(HttpStatus.OK).body("Comment Updated");
    }
}
