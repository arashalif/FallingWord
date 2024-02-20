package com.arashaghelifar.fallingword.domain.model


/**
 * Represents domain class of Stats in the Falling Words game.
 *
 * @property questionCount The total number of questions.
 * @property questionPlayedCount The number of questions played.
 * @property correctCount The number of correct answers.
 * @property remainingCount The number of remaining questions.
 * @property scoreCount The total score.
 * @property gameCount The number of games played.
 */
data class Stats(
    val questionCount : Int = 0,
    val questionPlayedCount : Int = 0,
    val correctCount : Int = 0,
    val remainingCount : Int = 0,
    val scoreCount: Long = 0,
    val gameCount:Int = 0
){
    operator fun plus(other: Stats): Stats {
        return Stats(
            questionCount + other.questionCount,
            questionPlayedCount + other.questionPlayedCount,
            correctCount + other.correctCount,
            remainingCount + other.remainingCount,
            scoreCount + other.scoreCount,
            gameCount + other.gameCount
        )
    }
}