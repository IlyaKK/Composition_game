package com.ilya.composition_game.domain.repository

import com.ilya.composition_game.domain.entity.GameSettings
import com.ilya.composition_game.domain.entity.Level
import com.ilya.composition_game.domain.entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}