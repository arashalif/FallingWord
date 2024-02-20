package com.arashaghelifar.fallingword.domain.mapper

import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.model.Question
import org.junit.Assert.*
import org.junit.Test

class GameToStatsMapperTest{
    @Test
    fun `map should correctly calculate stats from a game object`() {
        // Given
        val game = Game(
            questions = listOf(
                Question(questionWord = "Q1", suggestionWord = "S1", hashedAnswerWord = "A1", isCorrectAnswered = true, isPlayed = true, score = 10),
                Question(questionWord = "Q2", suggestionWord = "S2", hashedAnswerWord = "A2", isCorrectAnswered = false, isPlayed = true, score = 5),
                Question(questionWord = "Q3", suggestionWord = "S3", hashedAnswerWord = "A3", isCorrectAnswered = true, isPlayed = false, score = null)
            )
        )

        // When
        val stats = GameToStatsMapper.map(game)

        // Then
        assertEquals(3, stats.questionCount)
        assertEquals(2, stats.questionPlayedCount)
        assertEquals(1, stats.correctCount)
        assertEquals(1, stats.remainingCount)
        assertEquals(15, stats.scoreCount)
    }
}