package com.arashaghelifar.fallingword.domain.usecase

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.model.Stats
import com.arashaghelifar.fallingword.domain.repository.GameRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class TotalStatsUseCaseTest {

    private val gameRepository: GameRepository = mock()

    @Test
    fun `invoke returns total stats`() = runTest {
        // Mock data
        val totalStats = Stats()

        // Mock repository response
        whenever(gameRepository.fetchTotalStats()).thenReturn(flowOf(BaseResponse.Success(totalStats)))

        // Create instance of TotalStatsUseCase
        val totalStatsUseCase = TotalStatsUseCase(gameRepository)

        // Invoke the function and collect the result
        val result = totalStatsUseCase().toList()

        // Verify that the repository method was called
        verify(gameRepository).fetchTotalStats()

        // Verify the result
        assertEquals(listOf(BaseResponse.Success(totalStats)), result)
    }
}