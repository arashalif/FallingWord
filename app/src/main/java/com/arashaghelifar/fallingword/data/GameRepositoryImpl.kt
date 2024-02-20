package com.arashaghelifar.fallingword.data

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.data.local.GameDataStore
import com.arashaghelifar.fallingword.data.remote.WordsApi
import com.arashaghelifar.fallingword.data.remote.WordsDataSource
import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.model.Stats
import com.arashaghelifar.fallingword.domain.model.Word
import com.arashaghelifar.fallingword.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val wordsDataSource: WordsDataSource,
    private val gameDataStore: GameDataStore
) : GameRepository {

    override suspend fun fetchWords(): Flow<BaseResponse<List<Word>>> = wordsDataSource.fetchWords()

    override suspend fun fetchTotalStats(): Flow<BaseResponse<Stats>>  = gameDataStore.getStats()
    override suspend fun saveTotalStats(stats: Stats) {
        gameDataStore.saveStats(stats)
    }

    override suspend fun fetchPreviousGameStats(): Flow<BaseResponse<Game>> = gameDataStore.getPreviousGame()

    override suspend fun savePreviousGameStats(game: Game) {
        gameDataStore.savePreviousGame(game)
    }
}