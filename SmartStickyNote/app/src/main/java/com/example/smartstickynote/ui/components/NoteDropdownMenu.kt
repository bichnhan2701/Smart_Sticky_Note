package com.example.smartstickynote.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smartstickynote.R

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
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.pin_svgrepo_com),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        DropdownMenuItem(
            text = { Text("Edit") },
            onClick = onEdit,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.edit_3_svgrepo_com),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        DropdownMenuItem(
            text = { Text("Delete", color = Color.Red) },
            onClick = onDelete,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.delete_2_svgrepo_com),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(26.dp)
                )
            }
        )
    }
}