package com.arashaghelifar.fallingword.domain.usecase

import com.arashaghelifar.fallingword.common.BaseResponse
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
class InitialGameUseCaseTest {

    private val gameRepository: GameRepository = mock()

    @Test
    fun `invoke with error response`() = runTest {
        // Mock error response
        val error = "Error fetching words"
        val baseResponse = BaseResponse.Error(error)

        // Mock repository response
        whenever(gameRepository.fetchWords()).thenReturn(flowOf(baseResponse))

        // Create instance of InitialGameUseCase
        val initialGameUseCase = InitialGameUseCase(gameRepository)

        // Invoke the function and collect the result
        val result = initialGameUseCase().single()

        // Verify that the repository method was called
        verify(gameRepository).fetchWords()

        // Verify the result
        assertEquals(baseResponse, result)
    }
}