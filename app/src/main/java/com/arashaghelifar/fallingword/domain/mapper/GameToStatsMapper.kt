package com.arashaghelifar.fallingword.domain.mapper

import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.model.Stats

/**
 * Mapper object responsible for converting a [Game] object to a corresponding [Stats] object.
 *
 * This mapper calculates various statistics based on the provided game data.
 */
object GameToStatsMapper {

    /**
     * Maps a [Game] object to a [Stats] object.
     * @param game The game object to be mapped.
     * @return The mapped [Stats] object containing calculated statistics.
     */
    fun map(game: Game): Stats {

        return Stats(
            questionCount = game.questions.size,
            questionPlayedCount = game.questions.count { it.isPlayed },
            correctCount = game.questions.filter { it.isPlayed }.count { it.isCorrectAnswered },
            remainingCount = game.questions.count { !it.isPlayed },
            scoreCount = game.questions.filter { it.score != null }.sumOf { it.score ?: 0 }.toLong()
        )
    }
}