package com.example.smartstickynote.utils

import androidx.compose.ui.graphics.Color

fun getColorByPriority(priority: String): Color {
    return when (priority) {
        "High" -> Color(0xFFD1C4E9)
        "Medium" -> Color(0xFFB2EBF2)
        "Low" -> Color(0xFFC8E6C9)
        else -> Color.LightGray
    }
}