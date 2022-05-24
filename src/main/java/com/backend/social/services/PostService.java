package com.backend.social.services;

import com.backend.social.dto.PostDTO;
import com.backend.social.exceptions.InvalidPostException;
import com.backend.social.exceptions.UnauthorizedUserException;
import com.backend.social.models.PostEntity;
import com.backend.social.models.UserEntity;
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
public class PostService {

    @Autowired
    private ModelMapper modelMapperProvider;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public UserEntity getAuthenticatedUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        UserEntity user = userRepository.findByEmail(username);
        return user;
    }

    public void createPost(PostDTO post) {
        UserEntity user = getAuthenticatedUser();
        PostEntity postEntity = modelMapperProvider.map(post,PostEntity.class);
        postEntity.setUserId(user.getId());
        postRepository.save(postEntity);
    }

    public void deletePost(String id) throws InvalidPostException , UnauthorizedUserException {
        UserEntity user = getAuthenticatedUser();
        Optional<PostEntity> postEntity = postRepository.findById(id);
        if(postEntity.isPresent()){
            if(!(postEntity.get().getUserId()).equals(user.getId())){
                throw new UnauthorizedUserException("Unauthorized User");
            }
            postRepository.delete(postEntity.get());
        }
        else{
            throw new InvalidPostException("Post does not exist");
        }
    }

    public void updatePost(PostDTO post,String id) throws InvalidPostException,UnauthorizedUserException{
        UserEntity user = getAuthenticatedUser();
        Optional<PostEntity> postEntity = postRepository.findById(id);
        if(postEntity.isPresent()){
            if(!(postEntity.get().getUserId()).equals(user.getId())){
                throw new UnauthorizedUserException("Unauthorized User");
            }
            PostEntity updatePost = postEntity.get();
            updatePost.setPost(post.getPost());
            postRepository.save(updatePost);
        }
        else{
            throw new InvalidPostException("Post does not exist");
        }
    }

    public List<PostEntity> getAllPosts(){
        List<PostEntity> posts = postRepository.findAll();
        return posts;
    }
}
