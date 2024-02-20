package com.arashaghelifar.fallingword.domain.usecase

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.model.Stats
import com.arashaghelifar.fallingword.domain.repository.GameRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PreviousGameStatsUseCaseTest {
    @ExperimentalCoroutinesApi
    class PreviousGameStatsUseCaseTest {

        private val gameRepository: GameRepository = mock()

        @Test
        fun `invoke with success response`() = runTest {
            // Mock data
            val stats = Stats()
            val game = Game()

            // Mock PreviousGameUseCase response
            whenever(gameRepository.fetchPreviousGameStats()).thenReturn(flowOf(BaseResponse.Success(game)))

            val previousGameStatsUseCase = PreviousGameStatsUseCase(gameRepository)
            // Invoke the use case
            val result = previousGameStatsUseCase().single()

            // Verify that the repository method was called
            verify(gameRepository).fetchPreviousGameStats()

            // Verify the result
            assertEquals(BaseResponse.Success(stats), result)
        }

        @Test
        fun `invoke with error response`() = runTest {
            // Mock error response
            val error = "Error fetching previous game stats"
            val baseResponse = BaseResponse.Error(error)

            whenever(gameRepository.fetchPreviousGameStats()).thenReturn(flowOf(baseResponse))
            val previousGameStatsUseCase = PreviousGameStatsUseCase(gameRepository)

            // Invoke the use case
            val result = previousGameStatsUseCase().single()
            verify(gameRepository).fetchPreviousGameStats()

            // Verify the result
            assertEquals(baseResponse, result)
        }
    }
}