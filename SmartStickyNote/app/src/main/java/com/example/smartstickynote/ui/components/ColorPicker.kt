package com.example.smartstickynote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorPicker(
    colorOptions: List<Int>,
    selectedColor: Int,
    onColorSelected: (Int) -> Unit // Trả về Int thay vì Color
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(colorOptions) { colorInt ->
            val color = Color(colorInt)
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .padding(4.dp)
                    .background(color, shape = CircleShape)
                    .border(
                        width = if (selectedColor == colorInt) 3.dp else 1.dp,
                        color = if (selectedColor == colorInt) Color.Black else Color.Gray,
                        shape = CircleShape
                    )
                    .clickable { onColorSelected(colorInt) } // Trả về Int
            )
        }
    }
}