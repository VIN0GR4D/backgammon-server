package com.example.backgammon.service

import com.example.backgammon.model.AuthResponse
import com.example.backgammon.model.User
import com.example.backgammon.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenService: JwtTokenService
) {

    fun login(username: String, password: String): AuthResponse {
        val user = userRepository.findByUsername(username)
            ?: return AuthResponse(success = false, message = "Пользователь не найден")

        if (!passwordEncoder.matches(password, user.password)) {
            return AuthResponse(success = false, message = "Неверный пароль")
        }

        val token = jwtTokenService.generateToken(username)
        return AuthResponse(success = true, token = token, username = username)
    }

    fun register(username: String, email: String, password: String): AuthResponse {
        // Проверяем, что пользователь с таким именем или email не существует
        if (userRepository.existsByUsername(username)) {
            return AuthResponse(success = false, message = "Имя пользователя уже занято")
        }

        if (userRepository.existsByEmail(email)) {
            return AuthResponse(success = false, message = "Email уже зарегистрирован")
        }

        // Создаем нового пользователя
        val newUser = User(
            username = username,
            email = email,
            password = passwordEncoder.encode(password)
        )

        userRepository.save(newUser)

        // Генерируем токен и возвращаем успешный ответ
        val token = jwtTokenService.generateToken(username)
        return AuthResponse(success = true, token = token, username = username)
    }
}