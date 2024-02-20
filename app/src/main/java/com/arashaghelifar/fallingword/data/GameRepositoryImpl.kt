package com.arashaghelifar.fallingword.data

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.model.Stats
import com.arashaghelifar.fallingword.domain.model.Word
import com.arashaghelifar.fallingword.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor() : GameRepository {

    override suspend fun fetchWords(): Flow<BaseResponse<List<Word>>> = TODO("Not Implemented")

    override suspend fun fetchTotalStats(): Flow<BaseResponse<Stats>> = TODO("Not Implemented")
    override suspend fun saveTotalStats(stats: Stats) {
        TODO("Not Implemented")
    }

    override suspend fun fetchPreviousGameStats(): Flow<BaseResponse<Game>> =
        TODO("Not Implemented")

    override suspend fun savePreviousGameStats(game: Game) {
        TODO("Not Implemented")
    }
}