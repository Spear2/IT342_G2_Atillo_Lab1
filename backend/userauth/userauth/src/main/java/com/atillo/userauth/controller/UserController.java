package com.atillo.userauth.controller;

import com.atillo.userauth.entity.User;
import com.atillo.userauth.service.AuthService;
import org.antlr.v4.runtime.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private AuthService authService;

    public ResponseEntity<User> me(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer", "");
        return ResponseEntity.ok(authService.getUserFromToken(token));
    }
}
