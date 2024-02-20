package com.arashaghelifar.fallingword.domain.repository

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.model.Stats
import com.arashaghelifar.fallingword.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun fetchWords() : Flow<BaseResponse<List<Word>>>
    suspend fun fetchTotalStats() : Flow<BaseResponse<Stats>>
    suspend fun saveTotalStats(stats: Stats)
    suspend fun fetchPreviousGameStats() : Flow<BaseResponse<Game>>
    suspend fun savePreviousGameStats(game: Game)
}