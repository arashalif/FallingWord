package com.arashaghelifar.fallingword.domain.usecase

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case responsible for retrieving the previous game session from the repository.
 *
 * This use case fetches the previous game session from the [GameRepository].
 *
 * @property gameRepository Repository for accessing game data.
 */
class PreviousGameUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {
    /**
     * Retrieves the previous game session from the repository.
     *
     * This function fetches the previous game session from the game repository.
     *
     * @return A flow emitting the base response containing the previous game session.
     */
    suspend operator fun invoke(): Flow<BaseResponse<Game>> {
        return gameRepository.fetchPreviousGameStats()
    }
}
