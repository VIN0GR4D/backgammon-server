package com.example.backgammon.config

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate

@Configuration
class DatabaseInitializer {

    @Bean
    fun initDatabase(jdbcTemplate: JdbcTemplate): CommandLineRunner {
        return CommandLineRunner {
            try {
                // Проверяем существование таблицы USERS более безопасным способом
                val tablesCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'USERS'",
                    Int::class.java
                ) ?: 0

                if (tablesCount == 0) {
                    // Если таблица не существует, создаем ее
                    jdbcTemplate.execute("""
                        CREATE TABLE USERS (
                            ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                            USERNAME VARCHAR(50) NOT NULL UNIQUE,
                            EMAIL VARCHAR(100) NOT NULL UNIQUE,
                            PASSWORD VARCHAR(255) NOT NULL
                        )
                    """)
                    println("Таблица USERS успешно создана")
                } else {
                    println("Таблица USERS уже существует")
                }
            } catch (e: Exception) {
                // Игнорируем ошибки, если таблица уже существует
                println("Произошла ошибка при инициализации: ${e.message}")
                // Создаем таблицу в любом случае, так как это in-memory БД
                try {
                    jdbcTemplate.execute("""
                        CREATE TABLE IF NOT EXISTS USERS (
                            ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                            USERNAME VARCHAR(50) NOT NULL UNIQUE,
                            EMAIL VARCHAR(100) NOT NULL UNIQUE,
                            PASSWORD VARCHAR(255) NOT NULL
                        )
                    """)
                    println("Таблица USERS создана через CREATE IF NOT EXISTS")
                } catch (innerEx: Exception) {
                    println("Не удалось создать таблицу: ${innerEx.message}")
                }
            }
        }
    }
}