package com.example.smartstickynote.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartstickynote.R
import com.example.smartstickynote.domain.model.Category
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.ui.components.CategoryDropdown
import com.example.smartstickynote.ui.components.DeleteNoteDialog
import com.example.smartstickynote.ui.components.NoteContentItem
import com.example.smartstickynote.ui.viewmodel.CategoryViewModel
import com.example.smartstickynote.ui.viewmodel.NoteViewModel
import com.example.smartstickynote.utils.widget.WidgetUpdater

@Composable
fun EditNoteScreen(
    navController: NavController,
    noteId: String,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categories by categoryViewModel.categories.collectAsState()
    var selectedCategory by remember { mutableStateOf<Category?>(null) }

    val note by viewModel.getNoteById(noteId).collectAsState(initial = null)

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf("Medium") }

    var noteToDelete by remember { mutableStateOf<Note?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    // Cập nhật state khi note được load
    LaunchedEffect(note) {
        note?.let {
            title = it.title
            content = it.content
            selectedPriority = it.priorityRate
            selectedCategory = categories.find { category -> category.id == it.categoryId }
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
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    // Cập nhật note với thông tin mới
                    val updatedNote = note!!.copy(
                        title = title,
                        content = content,
                        priorityRate = selectedPriority,
                        categoryId = selectedCategory?.id ?: note!!.categoryId, // <-- cập nhật categoryId
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
                Spacer(modifier = Modifier.width(16.dp))
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
                Spacer(modifier = Modifier.width(16.dp))
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
                textStyle = TextStyle(
                    fontSize = 16.sp
                ),
                placeholder = { Text("Edit your title") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF9C55FF),
                    unfocusedBorderColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Chọn danh mục", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            CategoryDropdown(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )

            Spacer(modifier = Modifier.height(16.dp))
            // Mức độ ưu tiên
            Text("Chọn mức độ ưu tiên", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                listOf("Cao", "Trung bình", "Thấp").forEach { label ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedPriority == label,
                            onClick = { selectedPriority = label }
                        )
                        Text(label, fontWeight = FontWeight.Medium, fontSize = 16.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Nội dung
            Text("Content", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            NoteContentItem(
                value = content,
                onValueChange = { content = it }
            )
            Spacer(modifier = Modifier.height(50.dp))
        }

        // Dialog xóa
        if (showDeleteDialog) {
            DeleteNoteDialog(
                onConfirm = {
                    noteToDelete?.let {
                        viewModel.deleteNote(it)
                        WidgetUpdater.updateWidgetNow(context.applicationContext)
                        navController.popBackStack()
                    }
                    showDeleteDialog = false
                },
                onDismiss = { showDeleteDialog = false }
            )
        }
    }
}