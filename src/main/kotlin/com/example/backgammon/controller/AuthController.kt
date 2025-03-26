package com.example.backgammon.controller

import com.example.backgammon.model.AuthResponse
import com.example.backgammon.model.LoginRequest
import com.example.backgammon.model.RegisterRequest
import com.example.backgammon.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {
        val response = authService.login(request.username, request.password)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<AuthResponse> {
        val response = authService.register(
            request.username,
            request.email,
            request.password
        )
        return ResponseEntity.ok(response)
    }
}