package com.atillo.userauth.service;

import com.atillo.userauth.dto.LoginRequestDTO;
import com.atillo.userauth.dto.LoginResponseDTO;
import com.atillo.userauth.dto.RegisterDTO;
import com.atillo.userauth.entity.User;
import com.atillo.userauth.repository.UserRepository;
import com.atillo.userauth.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepo;
    private TokenProvider tokenProvider;
    private PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO dto){
        User user = userRepo.findByEmail(dto.getEmail()).
                orElseThrow(() -> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password!");
        }
        String token = tokenProvider.createToken(user.getUserId());
        return new LoginResponseDTO(token, user.getEmail());
    }

    public User registration(RegisterDTO dto){
        if(userRepo.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email Already Exists!");
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAddress(dto.getAddress());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setActive(true);

        return userRepo.save(user);
    }

    public User getUserFromToken(String token) {
        Long userId = tokenProvider.getUserIdFromToken(token);
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
