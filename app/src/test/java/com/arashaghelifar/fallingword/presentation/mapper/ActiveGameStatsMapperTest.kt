package com.arashaghelifar.fallingword.presentation.mapper

import com.arashaghelifar.fallingword.domain.model.Stats
import org.junit.Assert.*
import org.junit.Test

class ActiveGameStatsMapperTest {
    @Test
    fun `map with valid stats`() {
        val stats = Stats(
            questionPlayedCount = 10,
            questionCount = 20,
            correctCount = 7,
            scoreCount = 30,
            remainingCount = 5
        )

        val result = ActiveGameStatsMapper.map(stats)

        assertEquals(4, result.statsStrings.size) // Number of stats strings
    }
}