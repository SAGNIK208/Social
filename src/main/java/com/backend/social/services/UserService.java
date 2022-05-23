package com.backend.social.services;

import com.backend.social.dto.UserDTO;
import com.backend.social.models.UserEntity;
import com.backend.social.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapperProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity registerUserService(UserDTO user) throws Exception {

        if(userRepository.findByEmail(user.getEmail()) == null){
            UserEntity userEntity = modelMapperProvider.map(user,UserEntity.class);
            userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(userEntity);
            return userEntity;
        }
        else{
            throw new Exception("User already exists");
        }

    }
}
