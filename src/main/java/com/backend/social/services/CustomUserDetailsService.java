package com.backend.social.services;

import com.backend.social.models.MyUserDetails;
import com.backend.social.models.UserEntity;
import com.backend.social.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User does not exist");
        }

        return new MyUserDetails(user);
    }
}
