package com.arashaghelifar.fallingword.domain.usecase

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.mapper.GameToStatsMapper
import com.arashaghelifar.fallingword.domain.model.Stats
import com.arashaghelifar.fallingword.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case responsible for retrieving statistics from the previous game session.
 *
 * This use case fetches statistics from the [GameRepository] and transforms them into a base response containing
 * the statistics of the previous game session.
 *
 * @property gameRepository Repository for accessing previous game data.
 */
open class PreviousGameStatsUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {

    /**
     * Retrieves statistics from the previous game session.
     *
     * This function fetches statistics from the game repository and maps them to a base response containing
     * the statistics of the previous game session.
     *
     * @return A flow emitting the base response containing the statistics of the previous game session.
     */
    suspend operator fun invoke(): Flow<BaseResponse<Stats>> {
        return gameRepository.fetchPreviousGameStats().map {
            when (it) {
                is BaseResponse.Error -> BaseResponse.Error(error = it.error)
                BaseResponse.Loading -> BaseResponse.Loading
                is BaseResponse.Success -> BaseResponse.Success(GameToStatsMapper.map(it.data))
            }
        }
    }
}
