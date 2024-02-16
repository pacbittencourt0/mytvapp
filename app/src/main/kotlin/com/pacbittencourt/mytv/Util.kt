package com.pacbittencourt.mytv

object Util {
    fun addZeroLeft(number: Int): String {
        if (number in 1..9) {
            return "0$number"
        }
        return number.toString()
    }
}