package com.example.userauth.data.api

import com.example.userauth.data.dto.LoginRequestDTO
import com.example.userauth.data.dto.LoginResponseDTO
import com.example.userauth.data.dto.RegisterDTO
import com.example.userauth.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    // Matches your Spring Boot @PostMapping("/api/auth/login")
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequestDTO): Response<LoginResponseDTO>

    // Matches your Spring Boot @PostMapping("/api/auth/register")
    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterDTO): Response<User>

}