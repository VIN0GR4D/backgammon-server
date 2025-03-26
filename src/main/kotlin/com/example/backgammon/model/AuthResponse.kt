package com.example.backgammon.model

data class AuthResponse(
    val success: Boolean,
    val token: String? = null,
    val username: String? = null,
    val message: String? = null
)