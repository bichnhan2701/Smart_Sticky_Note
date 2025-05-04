package com.example.smartstickynote.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun GradientText(
    text: String,
    fontSize: TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Bold
) {
    val brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF555BFF),
            Color(0xFF55B5FF),
            Color(0xFF9C55FF),
            Color(0xFFD255FF),
            Color(0xFFF955FF)
        )
    )
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        style = TextStyle(brush = brush)
    )
}
