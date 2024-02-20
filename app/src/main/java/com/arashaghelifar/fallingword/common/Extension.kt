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

    /**
     * Calculates the percentage of one integer relative to another.
     *
     * @param portion The portion value to calculate the percentage for.
     * @return A string representation of the percentage.
     */
    fun Int.calculatePercentOf(portion : Int):String{
        return if(this > 0){
            "${((portion.toDouble() / this) * 100).toInt()}%"
        }else{
            "0%"
        }
    }
}