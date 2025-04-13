package com.example.backgammon.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("STATISTICS")
data class Statistics(
    @Id
    val userId: Long,
    val totalGames: Int = 0,
    val wins: Int = 0,
    val losses: Int = 0,
    val averageMovesPerGame: Int = 0,
    val longestWinStreak: Int = 0,
    val currentWinStreak: Int = 0
)