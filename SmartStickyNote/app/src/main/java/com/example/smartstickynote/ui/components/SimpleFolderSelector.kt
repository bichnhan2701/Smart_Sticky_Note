package com.example.smartstickynote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.smartstickynote.R

/**
 * Dữ liệu đơn giản đại diện cho thư mục
 */
data class SimpleFolderData(
    val id: String,
    val name: String,
    val color: String,
    val noteCount: Int = 0
)

/**
 * Component cho việc chọn thư mục đơn giản, không phụ thuộc vào domain model
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleFolderSelector(
    selectedFolder: SimpleFolderData?,
    folders: List<SimpleFolderData>,
    onFolderSelected: (SimpleFolderData?) -> Unit
) {
    var showFolderDialog by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showFolderDialog = true },
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 1.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.folder_open),
                contentDescription = "Folder",
                tint = if (selectedFolder != null) Color(android.graphics.Color.parseColor(selectedFolder.color)) 
                       else MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Text(
                text = selectedFolder?.name ?: "Không có thư mục",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Select Folder",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
    
    if (showFolderDialog) {
        SimpleFolderSelectionDialog(
            folders = folders,
            selectedFolder = selectedFolder,
            onFolderSelected = { folder ->
                onFolderSelected(folder)
                showFolderDialog = false
            },
            onDismiss = { showFolderDialog = false }
        )
    }
}

@Composable
fun SimpleFolderSelectionDialog(
    folders: List<SimpleFolderData>,
    selectedFolder: SimpleFolderData?,
    onFolderSelected: (SimpleFolderData?) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Chọn thư mục",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Tùy chọn "Không có thư mục"
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onFolderSelected(null) }
                        .padding(vertical = 12.dp)
                ) {
                    RadioButton(
                        selected = selectedFolder == null,
                        onClick = { onFolderSelected(null) }
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Text(
                        text = "Không có thư mục",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                
                Divider()
                
                // Danh sách thư mục
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp)
                ) {
                    folders.forEach { folder ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onFolderSelected(folder) }
                                .padding(vertical = 12.dp)
                        ) {
                            RadioButton(
                                selected = selectedFolder?.id == folder.id,
                                onClick = { onFolderSelected(folder) }
                            )
                            
                            Spacer(modifier = Modifier.width(8.dp))
                            
                            // Màu của thư mục
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(Color(android.graphics.Color.parseColor(folder.color)))
                            )
                            
                            Spacer(modifier = Modifier.width(8.dp))
                            
                            Text(
                                text = folder.name,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Hủy")
                    }
                }
            }
        }
    }
}