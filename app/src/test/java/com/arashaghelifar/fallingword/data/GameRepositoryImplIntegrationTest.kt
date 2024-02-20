package com.arashaghelifar.fallingword.data

import com.arashaghelifar.fallingword.data.local.GameDataStore
import com.arashaghelifar.fallingword.data.remote.WordsDataSource
import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.model.Stats
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock

class GameRepositoryImplIntegrationTest {

    private val wordsDataSource: WordsDataSource = mock()
    private val gameDataStore: GameDataStore = mock()
    private val gameRepository = GameRepositoryImpl(wordsDataSource, gameDataStore)


    @Test
    fun `fetchTotalStats should call getStats from GameDataStore`() {
        runTest {
            // When
            gameRepository.fetchTotalStats()

            // Then
            verify(gameDataStore).getStats()
        }

    }

    @Test
    fun `saveTotalStats should call saveStats from GameDataStore`() {
        runTest {

            // Given
            val stats = Stats()

            // When
            gameRepository.saveTotalStats(stats)

            // Then
            verify(gameDataStore).saveStats(stats)
        }
    }

    @Test
    fun `fetchPreviousGameStats should call getPreviousGame from GameDataStore`() {
        runTest {
            // When
            gameRepository.fetchPreviousGameStats()

            // Then
            verify(gameDataStore).getPreviousGame()
        }

    }

    @Test
    fun `savePreviousGameStats should call savePreviousGame from GameDataStore`() {

        runTest {

            // Given
            val game = Game()

            // When
            runBlocking { gameRepository.savePreviousGameStats(game) }

            // Then
            verify(gameDataStore).savePreviousGame(game)
        }
    }
}