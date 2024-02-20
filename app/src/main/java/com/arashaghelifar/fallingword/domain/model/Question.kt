package com.arashaghelifar.fallingword.domain.model

import com.arashaghelifar.fallingword.domain.Constants


/**
 * Represents domain class of Question in the Falling Words game.
 *
 * @property questionWord The first word displayed as the question.
 * @property suggestionWord The word presented as the suggestion.
 * @property hashedAnswerWord The hashed version of the correct answer word.
 * @property isCorrectAnswered Indicates whether the question has been correctly answered.
 * @property isPlayed Indicates whether the question has been played.
 * @property score The score earned for answering the question correctly.
 * @property timeToAnswerInSec The time limit to answer the question, in seconds.
 *
 */
data class Question(
    val questionWord: String,
    val suggestionWord: String,
    val hashedAnswerWord: String,
    val isCorrectAnswered: Boolean = false,
    val isPlayed: Boolean = false,
    val score: Int? = null,
    val timeToAnswerInSec: Int = Constants.TIME_TO_ANSWER_IN_SEC,
)
