package com.arashaghelifar.fallingword.presentation.mapper

import com.arashaghelifar.fallingword.domain.model.Stats
import org.junit.Assert.*
import org.junit.Test

class TotalStatsMapperTest {

    @Test
    fun `map with valid Stats`() {
        // Given
        val stats = Stats(
            questionCount = 20,
            correctCount = 15,
            gameCount = 5,
            scoreCount = 80
        )

        // When
        val result = TotalStatsMapper.map(stats)

        assertEquals(4, result.statsStrings.size)
        // Add assertions for statsStrings and buttonTitle
    }
}