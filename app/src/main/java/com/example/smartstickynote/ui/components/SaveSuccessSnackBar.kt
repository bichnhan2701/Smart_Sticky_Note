package com.example.smartstickynote.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SaveSuccessSnackbar(
    show: Boolean,
    onDismiss: () -> Unit
) {
    if (show) {
        LaunchedEffect(Unit) {
            delay(2000)
            onDismiss()
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Snackbar(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(12.dp)),
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ) {
                Text("Note saved successfully!")
            }
        }
    }
}
