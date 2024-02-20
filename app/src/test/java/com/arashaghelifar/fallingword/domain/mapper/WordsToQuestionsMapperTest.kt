package com.arashaghelifar.fallingword.domain.mapper

import com.arashaghelifar.fallingword.domain.model.Word
import org.junit.Assert.*
import org.junit.Test

class WordsToQuestionsMapperTest{
    @Test
    fun `map should return list of questions from given list of words`() {
        // Given
        val words = listOf(
            Word("apple", "manzana"),
            Word("banana", "plátano"),
            Word("orange", "naranja")
        )
        val selectedWords = listOf(words[0], words[1]) // Selecting first two words

        // When
        val result = WordsToQuestionsMapper.map(words, selectedWords)

        // Then
        assertEquals(2, result.size)
    }
}