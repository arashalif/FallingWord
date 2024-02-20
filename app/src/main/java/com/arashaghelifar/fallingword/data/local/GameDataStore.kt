package com.arashaghelifar.fallingword.data.local

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.model.Stats
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining methods for accessing and manipulating game-related data in the data store.
 */
interface GameDataStore {

    /**
     * Retrieves the statistics related to the current game session.
     *
     * @return A [Flow] emitting [BaseResponse] containing the statistics of the current game session.
     */
    fun getStats(): Flow<BaseResponse<Stats>>

    /**
     * Saves the statistics of the current game session.
     *
     * @param stats The statistics of the current game session to be saved.
     */
    suspend fun saveStats(stats: Stats)

    /**
     * Retrieves the data of the previous game session.
     *
     * @return A [Flow] emitting [BaseResponse] containing the data of the previous game session.
     */
    fun getPreviousGame(): Flow<BaseResponse<Game>>

    /**
     * Saves the data of the previous game session.
     *
     * @param game The data of the previous game session to be saved.
     */
    suspend fun savePreviousGame(game: Game)
}