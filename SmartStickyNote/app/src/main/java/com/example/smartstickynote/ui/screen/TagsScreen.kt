package com.example.smartstickynote.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartstickynote.domain.model.Tag
import com.example.smartstickynote.ui.components.ColorPicker
import com.example.smartstickynote.ui.viewmodel.TagViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagsScreen(
    navController: NavController,
    viewModel: TagViewModel = hiltViewModel()
) {
    val tags by viewModel.tags.collectAsState(initial = emptyList())
    var showAddTagDialog by remember { mutableStateOf(false) }
    var tagToEdit by remember { mutableStateOf<Tag?>(null) }
    var tagToDelete by remember { mutableStateOf<Tag?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quản lý thẻ") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddTagDialog = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("+", fontSize = 24.sp)
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tags) { tag ->
                    TagItem(
                        tag = tag,
                        onEditClick = { tagToEdit = tag },
                        onDeleteClick = { tagToDelete = tag }
                    )
                }
            }
        }
    }

    // Dialog thêm hoặc chỉnh sửa thẻ
    if (showAddTagDialog || tagToEdit != null) {
        TagDialog(
            tag = tagToEdit,
            onDismiss = {
                showAddTagDialog = false
                tagToEdit = null
            },
            onSave = { name, color ->
                if (tagToEdit != null) {
                    viewModel.updateTag(tagToEdit!!.copy(name = name, color = color))
                } else {
                    viewModel.addTag(
                        Tag(
                            id = UUID.randomUUID().toString(),
                            name = name,
                            color = color,
                            createdAt = System.currentTimeMillis()
                        )
                    )
                }
                showAddTagDialog = false
                tagToEdit = null
            }
        )
    }

    // Dialog xác nhận xóa thẻ
    if (tagToDelete != null) {
        AlertDialog(
            onDismissRequest = { tagToDelete = null },
            title = { Text("Xóa thẻ") },
            text = {
                Text(
                    "Bạn có chắc chắn muốn xóa thẻ \"${tagToDelete?.name}\"? " +
                            "Thẻ này sẽ bị xóa khỏi tất cả ghi chú đang sử dụng nó."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteTag(tagToDelete!!)
                        tagToDelete = null
                    }
                ) {
                    Text("Xóa", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { tagToDelete = null }) {
                    Text("Hủy")
                }
            }
        )
    }
}

@Composable
fun TagItem(
    tag: Tag,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.background,
        tonalElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Màu của thẻ
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color(android.graphics.Color.parseColor(tag.color)))
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Tên và số lượng ghi chú
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = tag.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Text(
                    text = "${tag.noteCount} ghi chú",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Các nút hành động
            IconButton(onClick = onEditClick) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Chỉnh sửa",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            IconButton(onClick = onDeleteClick) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Xóa",
                    tint = Color.Red
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagDialog(
    tag: Tag?,
    onDismiss: () -> Unit,
    onSave: (name: String, color: String) -> Unit
) {
    val isEdit = tag != null
    var tagName by remember { mutableStateOf(tag?.name ?: "") }
    var tagColor by remember { mutableStateOf(tag?.color ?: "#9C55FF") }
    val isValid = tagName.isNotBlank()

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = if (isEdit) "Chỉnh sửa thẻ" else "Thêm thẻ mới",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = tagName,
                    onValueChange = { tagName = it },
                    label = { Text("Tên thẻ") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Màu sắc",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                ColorPicker(
                    selectedColor = tagColor,
                    onColorSelected = { tagColor = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Hủy")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { onSave(tagName, tagColor) },
                        enabled = isValid
                    ) {
                        Text("Lưu")
                    }
                }
            }
        }
    }
} 