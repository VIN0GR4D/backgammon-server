package com.example.backgammon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BackgammonServerApplication

fun main(args: Array<String>) {
    runApplication<BackgammonServerApplication>(*args)
}