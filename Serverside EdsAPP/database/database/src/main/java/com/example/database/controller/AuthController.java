package com.example.database.controller;


import com.example.database.model.LoginRequest;
import com.example.database.model.User;
import com.example.database.service.AuthService;
import com.example.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private AuthService service;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<Object> authUser(@RequestBody LoginRequest loginReq){
        User validatedUser = service.validateUser(loginReq.getUsername(), loginReq.getPassword());
        if (validatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        else{
            return ResponseEntity.ok().build();
        }
    }
}
