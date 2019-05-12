package com.bruce.calculator

object StringUtils {
    fun isNumeric(value: String) : Boolean {
        try {
            value.toDouble()
        } catch (e: NumberFormatException) {
            return false
        }
        return true
    }
}


