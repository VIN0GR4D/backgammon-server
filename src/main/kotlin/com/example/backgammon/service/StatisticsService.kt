package com.example.backgammon.service

import com.example.backgammon.model.Statistics
import com.example.backgammon.repository.StatisticsRepository
import com.example.backgammon.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class StatisticsService(
    private val statisticsRepository: StatisticsRepository,
    private val userRepository: UserRepository
) {
    fun getStatisticsForUser(username: String): Statistics? {
        val user = userRepository.findByUsername(username) ?: return null
        return statisticsRepository.findByUserId(user.id!!)
            ?: Statistics(userId = user.id)
    }

    fun updateStatistics(username: String, statistics: Statistics): Statistics? {
        val user = userRepository.findByUsername(username) ?: return null

        val existingStats = statisticsRepository.findByUserId(user.id!!)
        val statsToSave = if (existingStats != null) {
            existingStats.copy(
                totalGames = statistics.totalGames,
                wins = statistics.wins,
                losses = statistics.losses,
                averageMovesPerGame = statistics.averageMovesPerGame,
                longestWinStreak = statistics.longestWinStreak,
                currentWinStreak = statistics.currentWinStreak
            )
        } else {
            statistics.copy(userId = user.id)
        }

        return statisticsRepository.save(statsToSave)
    }
}