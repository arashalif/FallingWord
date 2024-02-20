package com.arashaghelifar.fallingword.domain.mapper

import com.arashaghelifar.fallingword.domain.model.Word
import org.junit.Assert.*
import org.junit.Test

class RandomWordsMapperTest{
    @Test
    fun `map should return random subset of words`() {
        // Given
        val words = listOf(
            Word("apple", "manzana"),
            Word("banana", "plátano"),
            Word("orange", "naranja"),
            Word("grape", "uva")
        )
        val totalQuestions = 2
        val mapper = RandomWordsMapper

        // When
        val result = mapper.map(words, totalQuestions)

        // Then
        assertEquals(totalQuestions, result.size)
        // Verify that all words in the result are unique
        assertEquals(totalQuestions, result.toSet().size)
        // Verify that all words in the result are present in the original list
        result.forEach { assertTrue(it in words) }
    }
}