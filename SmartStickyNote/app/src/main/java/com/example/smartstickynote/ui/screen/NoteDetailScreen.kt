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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartstickynote.R
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.navigation.Screen
import com.example.smartstickynote.ui.components.DeleteNoteDialog
import com.example.smartstickynote.ui.components.NoteContentItem
import com.example.smartstickynote.ui.viewmodel.CategoryViewModel
import com.example.smartstickynote.ui.viewmodel.NoteViewModel
import com.example.smartstickynote.utils.getColorByPriority
import com.example.smartstickynote.utils.widget.WidgetUpdater

@Composable
fun NoteDetailScreen(
    navController: NavController,
    noteId: String,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val note by viewModel.getNoteById(noteId).collectAsState(initial = null)
    var noteToDelete by remember { mutableStateOf<Note?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val category by note?.let { it.categoryId?.let { it1 -> categoryViewModel.getCategoryById(it1).collectAsState(initial = null) } } ?: remember { mutableStateOf(null) }

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    if (note == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        
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
                        painter = painterResource(R.drawable.back_svgrepo_com),
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Row {
                    IconButton(onClick = { navController.navigate(Screen.Edit.createId(noteId)) }) {
                        Icon(
                            painter = painterResource(R.drawable.edit_3_svgrepo_com),
                            contentDescription = "Edit",
                            tint = Color.Black,
                            modifier = Modifier.size(28.dp)
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

            category?.let {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                       painter = painterResource(R.drawable.folder_svgrepo_com),
                        contentDescription = null,
                        tint = Color(it.color),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Danh mục: ${it.title} ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }


            // Mức độ ưu tiên
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = getColorByPriority(note!!.priorityRate)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mức độ ưu tiên: ${note!!.priorityRate}",
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                }
            }

            if (note!!.isPin) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Ghi chú đang được ghim trên màn hình chờ!",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            NoteContentItem(
                value = note!!.content,
                readOnly = true,
                onValueChange = {}
            )
            
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
                    WidgetUpdater.updateWidgetNow(context.applicationContext)
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
