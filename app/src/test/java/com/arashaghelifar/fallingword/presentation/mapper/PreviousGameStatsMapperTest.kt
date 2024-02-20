package com.arashaghelifar.fallingword.presentation.mapper

import com.arashaghelifar.fallingword.domain.model.Stats
import org.junit.Assert.*
import org.junit.Test

class PreviousGameStatsMapperTest {

    @Test
    fun `map with valid Stats`() {
        // Given
        val stats = Stats(
            questionPlayedCount = 10,
            questionCount = 20,
            correctCount = 8,
            scoreCount = 80,
            remainingCount = 5
        )

        // When
        val result = PreviousGameStatsMapper.map(stats)

        // Then
        assertEquals(4, result.statsStrings.size)
        // Add assertions for statsStrings and buttonTitle
    }
}