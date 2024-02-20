package com.arashaghelifar.fallingword.domain.model

/**
 * Represents domain class of Game in the Falling Words game.
 *
 * @property questions The list of questions in the game.
 */
data class Game(
    val questions: List<Question> = listOf(),
)
