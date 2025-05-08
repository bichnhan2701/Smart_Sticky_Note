package com.example.smartstickynote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBarWithButton(
    searchInput: String,
    onInputChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFB993D6),
                        Color(0xFF8CA6DB),
                        Color(0xFF5F97C4)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(4.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = searchInput,
                onValueChange = onInputChange,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(16.dp),
                placeholder = { Text("Tìm kiếm ghi chú...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Gray
                    )
                },
                singleLine = true,
                interactionSource = interactionSource,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF9C55FF),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color(0xFF9C55FF),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color(0xFFF8F8F8)
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        onSearchClick()
                    }
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                )
            )

            GradientButton(
                text = "Tìm",
                onClick = {
                    focusManager.clearFocus()
                    onSearchClick()
                },
                modifier = Modifier
                    .height(56.dp)
                    .defaultMinSize(minWidth = 80.dp)
            )
        }
    }
}
