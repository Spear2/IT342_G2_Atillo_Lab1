package com.example.userauth.data.dto

// This matches the JSON: { "email": "...", "password": "..." }
data class LoginRequestDTO(
    val email: String,
    val password: String
)