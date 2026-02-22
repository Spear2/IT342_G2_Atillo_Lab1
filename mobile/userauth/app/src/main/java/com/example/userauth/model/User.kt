package com.example.userauth.data.model

// I pulled these exact fields directly from your UML diagram!
data class User(
    val userId: Long,
    val email: String,
    val address: String?,
    val phoneNumber: String?,
    val time_created: String?,
    val active_inactive: Boolean
)