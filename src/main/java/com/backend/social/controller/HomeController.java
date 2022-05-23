package com.backend.social.controller;

import com.backend.social.dto.UserDTO;
import com.backend.social.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/social/v1")
public class HomeController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = {"multipart/form-data"})
    public ResponseEntity register(@Valid @ModelAttribute UserDTO userDTO) {

        try {
            userService.registerUserService(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("New User Created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
