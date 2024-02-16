package com.pacbittencourt.mytv.util

object StringUtils {
    fun addZeroLeft(number: Int): String {
        if (number in 1..9) {
            return "0$number"
        }
        return number.toString()
    }
}