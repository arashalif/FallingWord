package com.arashaghelifar.fallingword.domain.model

/**
 * Represents domain class of Question in the Falling Words game.
 *
 * This class is not limited to any specific language and can be used with other languages as well.
 *
 * @property mainWord The main word.
 * @property answeredWord The word that has been answered.
 */
data class Word(
    val mainWord :String,
    val answeredWord :String,
)
