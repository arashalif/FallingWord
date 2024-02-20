package com.arashaghelifar.fallingword.domain.mapper

import com.arashaghelifar.fallingword.common.Extension.encrypt
import com.arashaghelifar.fallingword.domain.model.Question
import com.arashaghelifar.fallingword.domain.model.Word
import kotlin.random.Random

/**
 * Mapper class to map a list of [Word] objects to a list of [Question] objects.
 */
object WordsToQuestionsMapper {

    /**
     * Maps a list of [Word] objects to a list of [Question] objects.
     * Each selected word will be mapped to a question with a suggestion word generated from the available words.
     * @param words The list of all available [Word] objects.
     * @param selectedWords The list of [Word] objects selected for creating questions.
     * @return A list containing mapped [Question] objects.
     */
    fun map(words: List<Word>, selectedWords: List<Word>): List<Question> {
        val questions = mutableListOf<Question>()

        for (word in selectedWords) {

            val suggestionWord = generateSuggestionWord(
                correctAnswer = word.answeredWord,
                words = words
            )

            questions.add(
                Question(
                    questionWord = word.mainWord,
                    hashedAnswerWord = word.answeredWord.encrypt(),
                    suggestionWord = suggestionWord,
                    isPlayed = false
                )
            )
        }
        return questions
    }

    /**
     * Generates a suggestion word based on the provided correct answer and list of available words.
     * @param correctAnswer The correct answer for the question.
     * @param words The list of available [Word] objects.
     * @return The generated suggestion word.
     */
    private fun generateSuggestionWord(correctAnswer: String, words: List<Word>): String {
        return if (Random.nextBoolean()) {
            correctAnswer
        } else {
            val randomIndex = Random.nextInt(words.size)
            words[randomIndex].answeredWord
        }
    }
}