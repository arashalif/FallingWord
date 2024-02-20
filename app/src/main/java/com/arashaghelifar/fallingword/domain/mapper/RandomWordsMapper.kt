package com.arashaghelifar.fallingword.domain.mapper

import com.arashaghelifar.fallingword.domain.model.Word
import kotlin.random.Random

object RandomWordsMapper {

    /**
     * Selects a random subset of [Word] objects from the provided list [words].
     * The function ensures that the selected subset does not contain duplicates.
     *
     * @param words The list of [Word] objects from which the random subset will be selected.
     * @param totalQuestions The total number of [Word] objects to select in the random subset.
     * @return A list containing a random subset of [Word] objects.
     */
    fun map(words: List<Word>, totalQuestions: Int): List<Word> {
        val randomSublist = mutableSetOf<Word>()

        while (randomSublist.size < totalQuestions) {
            val randomIndex = Random.nextInt(0, words.lastIndex)
            randomSublist.add(words[randomIndex])
        }
        return randomSublist.toList()
    }

}