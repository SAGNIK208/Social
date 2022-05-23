package com.backend.social.controller;

import com.backend.social.dto.LoginRequest;
import com.backend.social.dto.LoginResponse;
import com.backend.social.dto.UserDTO;
import com.backend.social.services.CustomUserDetailsService;
import com.backend.social.services.UserService;
import com.backend.social.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/social/v1")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/success")
    public String success(){
        return "Success";
    }

    @PostMapping(value = "/register", consumes = {"multipart/form-data"})
    public ResponseEntity register(@Valid @ModelAttribute UserDTO userDTO) {

        try {
            userService.registerUserService(userDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("New User Created");
    }

    @PostMapping(value = "/login", consumes = {"multipart/form-data"})
    public ResponseEntity login(@Valid @ModelAttribute LoginRequest loginRequest) {
        try {

           if(customUserDetailsService.loadUserByUsername(loginRequest.getUsername())!=null){
               authenticationManager.authenticate(
                       new UsernamePasswordAuthenticationToken(
                               loginRequest.getUsername(),loginRequest.getPassword()));
           }

        }catch(UsernameNotFoundException u){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(u.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password");
        }
        final UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(loginRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(jwt));
    }

}
