package com.arashaghelifar.fallingword.common

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
}