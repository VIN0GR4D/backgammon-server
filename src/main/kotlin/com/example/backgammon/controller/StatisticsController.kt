package com.example.backgammon.controller

import com.example.backgammon.model.Statistics
import com.example.backgammon.service.StatisticsService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/statistics")
class StatisticsController(private val statisticsService: StatisticsService) {

    @GetMapping
    fun getStatistics(@AuthenticationPrincipal userDetails: UserDetails?): ResponseEntity<Statistics> {
        if (userDetails == null) {
            return ResponseEntity.status(401).build()
        }

        val statistics = statisticsService.getStatisticsForUser(userDetails.username)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(statistics)
    }

    @PostMapping
    fun updateStatistics(
        @AuthenticationPrincipal userDetails: UserDetails?,
        @RequestBody statistics: Statistics
    ): ResponseEntity<Statistics> {
        if (userDetails == null) {
            return ResponseEntity.status(401).build()
        }

        val updatedStatistics = statisticsService.updateStatistics(userDetails.username, statistics)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(updatedStatistics)
    }
}