package com.arashaghelifar.fallingword.domain.usecase

import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.repository.GameRepository
import javax.inject.Inject

/**
 * Use case responsible for saving the current game session.
 *
 * This use case saves the current game session to the [GameRepository].
 *
 * @property gameRepository Repository for accessing word data.
 */
class SaveGameUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {
    suspend operator fun invoke(game: Game) {
        gameRepository.savePreviousGameStats(game)
    }
}
