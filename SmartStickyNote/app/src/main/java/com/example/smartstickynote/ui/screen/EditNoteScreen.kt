package com.example.smartstickynote.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartstickynote.R
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.navigation.Screen
import com.example.smartstickynote.ui.components.DeleteNoteDialog
import com.example.smartstickynote.ui.components.NoteContentItem
import com.example.smartstickynote.ui.viewmodel.NoteViewModel

@Composable
fun EditNoteScreen(
    navController: NavController,
    noteId: String,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val note by viewModel.getNoteById(noteId).collectAsState(initial = null)

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf("Medium") }

    var noteToDelete by remember { mutableStateOf<Note?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    // Cập nhật state khi note được load
    LaunchedEffect(note) {
        note?.let {
            title = it.title
            content = it.content
            selectedPriority = it.priorityRate
        }
    }

    if (note == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            // Header với Save, Delete, Cancel
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(150.dp))
                IconButton(onClick = {
                    if (title != note!!.title || content != note!!.content || selectedPriority != note!!.priorityRate) {
                        viewModel.updateNote(
                            note!!.copy(
                                title = title,
                                content = content,
                                priorityRate = selectedPriority
                            )
                        )
                    }
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

            Spacer(modifier = Modifier.height(8.dp))

            Text("Content", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            NoteContentItem(
                value = content,
                onValueChange = { content = it }
            )
        }
    }
    AnimatedVisibility(
        visible = showDeleteDialog && noteToDelete != null,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut()
    ) {
        noteToDelete?.let {
            DeleteNoteDialog(
                noteTitle = it.title,
                onConfirm = {
                    viewModel.deleteNote(it)
                    showDeleteDialog = false
                    noteToDelete = null
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true}
                    }
                },
                onDismiss = {
                    showDeleteDialog = false
                    noteToDelete = null
                }
            )
        }
    }
}