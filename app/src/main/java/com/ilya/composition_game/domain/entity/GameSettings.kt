package com.ilya.composition_game.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightValue: Int,
    val minPercentOfRightAnswer: Int,
    val gameTimeInSeconds: Int
) : Parcelable
