package com.backend.social.services;

import com.backend.social.dto.CommentDTO;
import com.backend.social.exceptions.InvalidCommentException;
import com.backend.social.exceptions.InvalidPostException;
import com.backend.social.exceptions.UnauthorizedUserException;
import com.backend.social.models.CommentEntity;
import com.backend.social.models.PostEntity;
import com.backend.social.models.UserEntity;
import com.backend.social.repositories.CommentRepository;
import com.backend.social.repositories.PostRepository;
import com.backend.social.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapperProvider;

    public UserEntity getAuthenticatedUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        UserEntity user = userRepository.findByEmail(username);
        return user;
    }

    public void createComment(CommentDTO comment,String postId) throws InvalidPostException {
        UserEntity user = getAuthenticatedUser();
        Optional<PostEntity> post = postRepository.findById(postId);
        if(post.isPresent()){
            CommentEntity commentEntity = modelMapperProvider.map(comment,CommentEntity.class);
            commentEntity.setUserId(user.getId());
            commentEntity.setPostId(post.get().getId());
            post.get().addComment(commentEntity);
            commentRepository.save(commentEntity);
            postRepository.save(post.get());
        }
        else{
            throw new InvalidPostException("Post does not exist");
        }
    }

    public void deleteComment(String postId,String commentId) throws InvalidPostException , UnauthorizedUserException , InvalidCommentException {
        UserEntity user = getAuthenticatedUser();
        Optional<PostEntity> post = postRepository.findById(postId);
        if(post.isPresent()){
            Optional<CommentEntity> comment = commentRepository.findById(commentId);
            if(comment.isPresent()){
                if(!(comment.get().getUserId()).equals(user.getId())){
                    throw new UnauthorizedUserException("Unauthorized User");
                }
                post.get().deleteComment(comment.get());
                postRepository.save(post.get());
                commentRepository.delete(comment.get());
            }
            else{
                throw new InvalidCommentException("Comment does not exist");
            }
        }
        else{
            throw new InvalidPostException("Post does not exist");
        }
    }

    public void updateComment(String postId,String commentId,CommentDTO commentDTO) throws InvalidPostException , UnauthorizedUserException , InvalidCommentException {
        UserEntity user = getAuthenticatedUser();
        Optional<PostEntity> post = postRepository.findById(postId);
        if(post.isPresent()){
            Optional<CommentEntity> comment = commentRepository.findById(commentId);
            if(comment.isPresent()){
                if(!(comment.get().getUserId()).equals(user.getId())){
                    throw new UnauthorizedUserException("Unauthorized User");
                }
                CommentEntity oldComment = comment.get();
                CommentEntity updatedComment = comment.get();
                updatedComment.setComment(commentDTO.getComment());
                post.get().updateComment(updatedComment);
                postRepository.save(post.get());
                commentRepository.save(updatedComment);
            }
            else{
                throw new InvalidCommentException("Comment does not exist");
            }
        }
        else{
            throw new InvalidPostException("Post does not exist");
        }
    }

}
