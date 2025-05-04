package com.example.smartstickynote.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun NoteDropdownMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onPin: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    DropdownMenu(expanded = expanded, onDismissRequest = onDismiss) {
        DropdownMenuItem(
            text = { Text("Pin on screen") },
            onClick = onPin,
            leadingIcon = { Icon(Icons.Default.Build, contentDescription = null) }
        )
        DropdownMenuItem(
            text = { Text("Edit") },
            onClick = onEdit,
            leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) }
        )
        DropdownMenuItem(
            text = { Text("Delete", color = Color.Red) },
            onClick = onDelete,
            leadingIcon = {
                Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Red)
            }
        )
    }
}