package com.arashaghelifar.fallingword.domain.usecase

import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.repository.GameRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class SaveGameUseCaseTest {

    private val gameRepository:GameRepository = mock()

    @Test
    fun `invoke saves game`() = runTest {
        // Mock data
        val game = Game()

        // Create instance of SaveGameUseCase
        val saveGameUseCase = SaveGameUseCase(gameRepository)

        // Invoke the function
        saveGameUseCase(game)

        // Verify that the repository method was called with the correct arguments
        verify(gameRepository).savePreviousGameStats(game)
    }
}