package com.example.smartstickynote.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartstickynote.R
import com.example.smartstickynote.domain.model.Folder
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.domain.model.Tag
import com.example.smartstickynote.navigation.Screen
import com.example.smartstickynote.ui.components.AutoCategorySection
import com.example.smartstickynote.ui.components.DeleteNoteDialog
import com.example.smartstickynote.ui.components.TagItem
import com.example.smartstickynote.ui.viewmodel.NoteViewModel
import com.example.smartstickynote.utils.getColorByPriority
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDateTimeString(): String {
    val sdf = SimpleDateFormat("hh:mm a dd/MM/yyyy", Locale.getDefault())
    return sdf.format(Date(this))
}

@Composable
fun NoteDetailScreen(
    navController: NavController,
    noteId: String,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val note by viewModel.getNoteById(noteId).collectAsState(initial = null)
    val tagsForNote by viewModel.getTagsForNote(noteId).collectAsState(initial = emptyList())
    val folders by viewModel.folders.collectAsState(initial = emptyList())
    
    var noteToDelete by remember { mutableStateOf<Note?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    
    val scrollState = rememberScrollState()

    if (note == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        // Tìm thư mục dựa vào folderId của note
        val selectedFolder = note?.folderId?.let { folderId ->
            folders.find { it.id == folderId }
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            // Header với Edit, Delete, Back
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = "Back",
                        tint = Color.Gray
                    )
                }

                Row {
                    IconButton(onClick = { navController.navigate(Screen.Edit.createId(noteId)) }) {
                        Icon(
                            painter = painterResource(R.drawable.edit),
                            contentDescription = "Edit",
                            tint = Color(0xFF9C55FF)
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
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tiêu đề ghi chú
            Text(
                text = note!!.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Thông tin về thời gian
            val dateFormatter = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault())
            val formattedDate = dateFormatter.format(java.util.Date(note!!.updatedAt))
            
            Text(
                text = "Cập nhật lần cuối: $formattedDate",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))
            
            // Hiển thị thư mục (nếu có)
            selectedFolder?.let { folder ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.folder_open),
                        contentDescription = "Folder",
                        tint = Color(android.graphics.Color.parseColor(folder.color)),
                        modifier = Modifier.size(16.dp)
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Text(
                        text = folder.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
            }
            
            // Hiển thị các thẻ
            if (tagsForNote.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    tagsForNote.forEach { tag ->
                        TagItem(
                            tag = tag,
                            onRemove = { /* Không cho phép xóa ở màn hình chi tiết */ }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Mức độ ưu tiên
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = getColorByPriority(note!!.priorityRate).copy(alpha = 0.1f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mức độ ưu tiên: ${note!!.priorityRate}",
                        fontWeight = FontWeight.Medium,
                        color = getColorByPriority(note!!.priorityRate)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nội dung ghi chú
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                tonalElevation = 1.dp
            ) {
                Text(
                    text = note!!.content,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Hiển thị danh mục tự động
            if (note!!.autoCategories.isNotEmpty()) {
                Divider()
                Spacer(modifier = Modifier.height(16.dp))
                
                AutoCategorySection(
                    autoCategories = note!!.autoCategories,
                    onCategorySelected = { /* Không cần xử lý ở đây */ }
                )
            }
            
            Spacer(modifier = Modifier.height(50.dp))
        }
    }

    AnimatedVisibility(
        visible = showDeleteDialog && noteToDelete != null,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut()
    ) {
        noteToDelete?.let {
            DeleteNoteDialog(
                onConfirm = {
                    viewModel.deleteNote(it)
                    showDeleteDialog = false
                    noteToDelete = null
                    navController.popBackStack()
                },
                onDismiss = {
                    showDeleteDialog = false
                    noteToDelete = null
                }
            )
        }
    }
}
