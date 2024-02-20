package com.arashaghelifar.fallingword.domain.usecase

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.repository.GameRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PreviousGameUseCaseTest {

    private val gameRepository: GameRepository = mock()

    @Test
    fun `invoke with success response`() = runTest {
        // Mock data
        val game = Game()

        // Mock repository response
        whenever(gameRepository.fetchPreviousGameStats()).thenReturn(
            flowOf(
                BaseResponse.Success(
                    game
                )
            )
        )

        // Create instance of PreviousGameUseCase
        val previousGameUseCase = PreviousGameUseCase(gameRepository)

        // Invoke the function and collect the result
        val result = previousGameUseCase().single()

        // Verify that the repository method was called
        verify(gameRepository).fetchPreviousGameStats()

        // Verify the result
        assertEquals(BaseResponse.Success(game), result)
    }

    @Test
    fun `invoke with error response`() = runTest {
        // Mock error response
        val error = "Error fetching previous game"
        val baseResponse = BaseResponse.Error(error)

        // Mock repository response
        whenever(gameRepository.fetchPreviousGameStats()).thenReturn(flowOf(baseResponse))

        // Create instance of PreviousGameUseCase
        val previousGameUseCase = PreviousGameUseCase(gameRepository)

        // Invoke the function and collect the result
        val result = previousGameUseCase().single()

        // Verify that the repository method was called
        verify(gameRepository).fetchPreviousGameStats()

        // Verify the result
        assertEquals(baseResponse, result)
    }
}