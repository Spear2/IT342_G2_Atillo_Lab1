package com.atillo.userauth.controller;

import com.atillo.userauth.entity.User;
import com.atillo.userauth.service.AuthService;
import org.antlr.v4.runtime.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
// Add 'allowedHeaders' to your annotation
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
public class UserController {
    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> me(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer ", "");
        return ResponseEntity.ok(authService.getUserFromToken(token));
    }
}
