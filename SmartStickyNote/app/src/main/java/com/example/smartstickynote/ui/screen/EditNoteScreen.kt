package com.example.smartstickynote.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartstickynote.R
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.domain.model.Tag
import com.example.smartstickynote.navigation.Screen
import com.example.smartstickynote.ui.components.AutoCategoryChips
import com.example.smartstickynote.ui.components.DeleteNoteDialog
import com.example.smartstickynote.ui.components.NoteContentItem
import com.example.smartstickynote.ui.components.TagList
import com.example.smartstickynote.ui.components.SimpleFolderData
import com.example.smartstickynote.ui.components.SimpleFolderSelector
import com.example.smartstickynote.ui.components.TagSelectionDialog
import com.example.smartstickynote.ui.components.TagsRow
import com.example.smartstickynote.ui.viewmodel.NoteViewModel

@Composable
fun EditNoteScreen(
    navController: NavController,
    noteId: String,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val note by viewModel.getNoteById(noteId).collectAsState(initial = null)
    val tagsForNote by viewModel.getTagsForNote(noteId).collectAsState(initial = emptyList())

    // Danh sách thư mục và thẻ
    val folders by viewModel.folders.collectAsState(initial = emptyList())
    val allTags by viewModel.tags.collectAsState(initial = emptyList())

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf("Medium") }
    // Chuyển sang sử dụng SimpleFolderData cho UI
    var selectedSimpleFolder by remember { mutableStateOf<SimpleFolderData?>(null) }
    var selectedTags by remember { mutableStateOf<List<Tag>>(emptyList()) }
    var autoCategories by remember { mutableStateOf<List<String>>(emptyList()) }
    
    var showTagDialog by remember { mutableStateOf(false) }
    var noteToDelete by remember { mutableStateOf<Note?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    
    val scrollState = rememberScrollState()
    
    // Chuyển đổi danh sách Folder thành SimpleFolderData cho UI
    val simpleFolders = folders.map { folder ->
        SimpleFolderData(
            id = folder.id,
            name = folder.name,
            color = folder.color,
            noteCount = folder.noteCount
        )
    }
    
    // Cập nhật state khi note được load
    LaunchedEffect(note, tagsForNote) {
        note?.let {
            title = it.title
            content = it.content
            selectedPriority = it.priorityRate
            autoCategories = it.autoCategories
            
            // Cập nhật SimpleFolderData từ folderId
            selectedSimpleFolder = if (it.folderId != null) {
                simpleFolders.find { folder -> folder.id == it.folderId }
            } else null
            
            // Cập nhật các thẻ đã chọn
            selectedTags = tagsForNote
        }
    }

    if (note == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            // Header với Save, Delete, Cancel
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(48.dp))
                
                IconButton(onClick = {
                    // Cập nhật note với thông tin mới
                    val updatedNote = note!!.copy(
                        title = title,
                        content = content,
                        priorityRate = selectedPriority,
                        folderId = selectedSimpleFolder?.id,
                        tags = selectedTags,
                        updatedAt = System.currentTimeMillis()
                    )
                    viewModel.updateNote(updatedNote)
                    navController.popBackStack()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.mingcute_save_line),
                        contentDescription = "Save",
                        tint = Color(0xFF4CAF50)
                    )
                }

                IconButton(onClick = {
                    noteToDelete = note
                    showDeleteDialog = true
                }) {
                    Icon(
                        painter = painterResource(R.drawable.delete_2_svgrepo_com),
                        contentDescription = "Delete",
                        tint = Color(0xFFFF5722)
                    )
                }

                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(R.drawable.close),
                        contentDescription = "Cancel",
                        tint = Color(0xFFF44336)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Tiêu đề
            Text("Title", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                placeholder = { Text("Edit your title") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF9C55FF),
                    unfocusedBorderColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Mức độ ưu tiên
            Text("Choose the priority rate", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                listOf("High", "Medium", "Low").forEach { label ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedPriority == label,
                            onClick = { selectedPriority = label }
                        )
                        Text(label, fontWeight = FontWeight.Medium, fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            // Chọn thư mục
            Text(
                "Folder",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            SimpleFolderSelector(
                selectedFolder = selectedSimpleFolder,
                folders = simpleFolders,
                onFolderSelected = { newFolder ->
                    selectedSimpleFolder = newFolder
                }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Chọn thẻ
            Text(
                "Tags",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            TagsRow(
                selectedTags = selectedTags,
                onTagRemoved = { tag -> 
                    selectedTags = selectedTags.filterNot { it.id == tag.id }
                },
                onAddTagClick = { showTagDialog = true }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nội dung
            Text("Content", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            NoteContentItem(
                value = content,
                onValueChange = { content = it }
            )
            
            // Hiển thị các danh mục tự động
            if (autoCategories.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Divider()
                Spacer(modifier = Modifier.height(16.dp))
                
                AutoCategoryChips(
                    categories = autoCategories,
                    onCategoryClick = { /* Có thể thêm xử lý khi người dùng nhấp vào */ }
                )
            }
            
            // Thêm khoảng trống cuối cùng
            Spacer(modifier = Modifier.height(50.dp))
        }
        
        // Dialog chọn thẻ
        if (showTagDialog) {
            TagSelectionDialog(
                availableTags = allTags,
                selectedTags = selectedTags,
                onTagSelected = { tag ->
                    selectedTags = selectedTags + tag
                },
                onTagDeselected = { tag ->
                    selectedTags = selectedTags.filterNot { it.id == tag.id }
                },
                onDismiss = { showTagDialog = false }
            )
        }
        
        // Dialog xóa
        if (showDeleteDialog) {
            DeleteNoteDialog(
                onConfirm = {
                    noteToDelete?.let {
                        viewModel.deleteNote(it)
                        navController.popBackStack()
                    }
                    showDeleteDialog = false
                },
                onDismiss = { showDeleteDialog = false }
            )
        }
    }
}