package com.arashaghelifar.fallingword.domain.usecase

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.mapper.GameToStatsMapper
import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.repository.GameRepository
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**
 * Use case responsible for saving the total statistics including the current game session.
 *
 * This use case fetches the total statistics from the [GameRepository], aggregates them with the statistics
 * of the current game session, and saves the updated total statistics back to the repository.
 *
 * @property gameRepository Repository for accessing game data.
 * @property saveGameUseCase Use case for saving the current game session.
 */
class SaveTotalStatsUseCase @Inject constructor(
    private val gameRepository: GameRepository,
    private val saveGameUseCase: SaveGameUseCase
) {

    /**
     * Saves the total statistics including the current game session to the repository.
     *
     * This function fetches the total statistics from the game repository, aggregates them with the statistics
     * of the provided game session, and saves the updated total statistics back to the repository.
     *
     * @param game The current game session to be included in the total statistics.
     */
    suspend operator fun invoke(game: Game) {
        gameRepository.fetchTotalStats().collectLatest {
            if (it is BaseResponse.Success) {
                saveGameUseCase(Game())
                val aggregatedStats = it.data + GameToStatsMapper.map(game)
                gameRepository.saveTotalStats(aggregatedStats)
            }
        }
    }
}
