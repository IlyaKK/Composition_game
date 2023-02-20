package com.ilya.composition_game.domain.usecases

import com.ilya.composition_game.domain.entity.GameSettings
import com.ilya.composition_game.domain.entity.Level
import com.ilya.composition_game.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}