package com.example.userauth.data.dto

// Make sure these match the exact field names in your Spring Boot RegisterDTO!
data class RegisterDTO(
    val email: String,
    val password: String,
    // Add these if your backend requires them during registration:
    // val phoneNumber: String? = null,
    // val address: String? = null
)