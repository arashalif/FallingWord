package com.arashaghelifar.fallingword.data.remote.mapper

import com.arashaghelifar.fallingword.data.remote.model.WordDataModel
import com.arashaghelifar.fallingword.domain.model.Word
import org.junit.Assert.*
import org.junit.Test

class WordDataModelsToWordsMapperTest{

    @Test
    fun `map should return list of Word objects`() {
        // Given
        val wordDataModels = listOf(
            WordDataModel("hello", "hola"),
            WordDataModel("goodbye", "adiós")
        )

        // When
        val result = WordDataModelsToWordsMapper.map(wordDataModels)

        // Then
        val expected = listOf(
            Word("hello", "hola"),
            Word("goodbye", "adiós")
        )
        assertEquals(expected, result)
    }
}