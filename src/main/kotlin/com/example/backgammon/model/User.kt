package com.example.backgammon.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("USERS")
data class User(
    @Id
    val id: Long? = null,
    val username: String,
    val email: String,
    val password: String
)