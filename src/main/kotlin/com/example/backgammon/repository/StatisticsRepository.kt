package com.example.backgammon.repository

import com.example.backgammon.model.Statistics
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StatisticsRepository : CrudRepository<Statistics, Long> {
    fun findByUserId(userId: Long): Statistics?
}