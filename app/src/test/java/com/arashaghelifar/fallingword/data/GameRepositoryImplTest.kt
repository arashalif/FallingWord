package com.arashaghelifar.fallingword.data

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.data.local.GameDataStore
import com.arashaghelifar.fallingword.data.remote.WordsDataSource
import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.model.Stats
import com.arashaghelifar.fallingword.domain.model.Word
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GameRepositoryImplTest {

    private val wordsDataSource: WordsDataSource = mock()
    private val gameDataStore: GameDataStore = mock()
    private val gameRepository = GameRepositoryImpl(wordsDataSource, gameDataStore)

    @Test
    fun `fetchWords should return flow of BaseResponse containing list of words`() {
        runTest {
            // Given
            val mockWords = listOf(Word("apple", "manzana"), Word("banana", "plátano"))
            whenever(wordsDataSource.fetchWords()).thenReturn(flow {
                emit(
                    BaseResponse.Success(
                        mockWords
                    )
                )
            })

            // When
            val resultFlow = runBlocking { gameRepository.fetchWords() }


            // Then
            val result = resultFlow.first()
            assertTrue(result is BaseResponse.Success)
            assertEquals(mockWords, (result as BaseResponse.Success).data)
        }
    }

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
