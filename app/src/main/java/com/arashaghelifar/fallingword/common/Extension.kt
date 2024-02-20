package com.arashaghelifar.fallingword.common

import java.security.MessageDigest

object Extension {
    /**
     * Extension function to encrypt a string using SHA-256 algorithm.
     * @return The encrypted string.
     */
    fun String.encrypt(): String {
        val bytes = this.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.joinToString("") { "%02x".format(it) }
    }
}