package com.example.smartstickynote.utils

import androidx.compose.ui.graphics.Color

fun getColorByPriority(priority: String): Color {
    return when (priority) {
        "Cao" -> Color(0xFFD1C4E9)
        "Trung bình" -> Color(0xFFB2EBF2)
        "Thấp" -> Color(0xFFC8E6C9)
        else -> Color.LightGray
    }
}