package com.arashaghelifar.fallingword.domain.usecase

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.model.Stats
import com.arashaghelifar.fallingword.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case responsible for retrieving the total statistics from the repository.
 *
 * This use case fetches the total statistics from the [GameRepository].
 *
 * @property gameRepository Repository for accessing game data.
 */
class TotalStatsUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {

    /**
     * Retrieves the total statistics from the repository.
     *
     * This function fetches the total statistics from the game repository.
     *
     * @return A flow emitting the base response containing the total statistics.
     */
    suspend operator fun invoke(): Flow<BaseResponse<Stats>> {
        return gameRepository.fetchTotalStats()
    }
}
