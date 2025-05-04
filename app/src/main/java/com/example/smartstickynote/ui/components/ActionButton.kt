package com.example.smartstickynote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActionButton(
    text: String?,
    icon: ImageVector? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    iconBackgroundColor: Color = Color(0xFF4D7ED9),
    contentColor: Color = Color(0xFF4D7ED9),
    iconSize: Dp = 18.dp
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = containerColor,
        shadowElevation = 4.dp,
        modifier = modifier
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            // Nếu icon được truyền vào thì hiển thị
            if (icon != null) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(color = iconBackgroundColor, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = text,
                        tint = Color.White,
                        modifier = Modifier.size(iconSize)
                    )
                }
            }

            if (icon != null && text != null) {
                Spacer(modifier = Modifier.width(8.dp))
            }

            if (text != null) {
                Text(
                    text = text,
                    color = contentColor,
                    fontSize = 16.sp
                )
            }
        }
    }
}