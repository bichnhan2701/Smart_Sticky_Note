package com.example.smartstickynote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorPicker(
    selectedColor: String,
    onColorSelected: (String) -> Unit
) {
    val colors = listOf(
        "#9C55FF",  // Tím
        "#FF5252",  // Đỏ
        "#FF9800",  // Cam
        "#FFEB3B",  // Vàng
        "#4CAF50",  // Xanh lá
        "#03A9F4",  // Xanh dương
        "#3F51B5",  // Xanh dương đậm
        "#9C27B0",  // Tím đậm
        "#795548",  // Nâu
        "#607D8B"   // Xám xanh
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(colors) { colorHex ->
            ColorItem(
                color = colorHex,
                isSelected = colorHex == selectedColor,
                onClick = { onColorSelected(colorHex) }
            )
        }
    }
}

@Composable
fun ColorItem(
    color: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(Color(android.graphics.Color.parseColor(color)))
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = Color.White,
                shape = CircleShape
            )
            .clickable(onClick = onClick)
    ) {
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = Color.White
            )
        }
    }
} 