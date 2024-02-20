package com.arashaghelifar.fallingword.common

import com.arashaghelifar.fallingword.common.Extension.calculatePercentOf
import com.arashaghelifar.fallingword.common.Extension.encrypt
import org.junit.Assert.*
import org.junit.Test

class ExtensionTest{

    @Test
    fun `encrypt should return encrypted string using SHA-256 algorithm`() {
        // Given
        val input = "password"
        val expectedOutput = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8"

        // When
        val result = input.encrypt()

        // Then
        assertEquals(expectedOutput, result)
    }

    @Test
    fun `calculatePercentOf with positive total and portion`() {
        // Given
        val total = 100
        val portion = 25

        // When
        val result = total.calculatePercentOf(portion)

        // Then
        assertEquals("25%", result)
    }

    @Test
    fun `calculatePercentOf with zero total and positive portion`() {
        // Given
        val total = 0
        val portion = 25

        // When
        val result = total.calculatePercentOf(portion)

        // Then
        assertEquals("0%", result)
    }

    @Test
    fun `calculatePercentOf with positive total and zero portion`() {
        // Given
        val total = 100
        val portion = 0

        // When
        val result = total.calculatePercentOf(portion)

        // Then
        assertEquals("0%", result)
    }

    @Test
    fun `calculatePercentOf with zero total and zero portion`() {
        // Given
        val total = 0
        val portion = 0

        // When
        val result = total.calculatePercentOf(portion)

        // Then
        assertEquals("0%", result)
    }

    @Test
    fun `calculatePercentOf with negative total`() {
        // Given
        val total = -100
        val portion = 25

        // When
        val result = total.calculatePercentOf(portion)

        // Then
        assertEquals("0%", result)
    }
}
