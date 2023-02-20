package com.ilya.composition_game.domain.entity

data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightValue: Int,
    val minPercentOfRightAnswer: Int,
    val gameTimeInSeconds: Int
)
