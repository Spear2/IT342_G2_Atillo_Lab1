package com.example.userauth.data.dto

// This expects the JWT token your Spring Boot server sends back
data class LoginResponseDTO(
    val token: String,
    val message: String? = null
)