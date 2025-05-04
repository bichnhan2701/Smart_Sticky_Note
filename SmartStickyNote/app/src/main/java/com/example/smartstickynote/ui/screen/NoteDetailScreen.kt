package com.example.smartstickynote.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
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
    var noteToDelete by remember { mutableStateOf<Note?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (note == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val priorityColor = getColorByPriority(note!!.priorityRate)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Top actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(R.drawable.back_svgrepo_com),
                        contentDescription = "Back",
                        modifier = Modifier.size(30.dp)
                    )
                }
                Row {
                    IconButton(onClick = {
                        navController.navigate(Screen.Edit.createId(note!!.id))
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.edit_3_svgrepo_com),
                            contentDescription = "Edit",
                            modifier = Modifier.size(30.dp)
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
                            tint = Color.Red,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Title
            Text(
                text = note!!.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            // Created date
            Text(
                text = "Created at ${note!!.createdAt.toDateTimeString()}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Priority rate
            Text("Priority rate", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                listOf("High", "Medium", "Low").forEach { label ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = note!!.priorityRate == label,
                            onClick = null,
                            enabled = false
                        )
                        Text(text = label, fontSize = 16.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Pin to screen
            Text("Pin on your screen", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                listOf("Yes" to true, "No" to false).forEach { (label, value) ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = note!!.isPin == value,
                            onClick = null,
                            enabled = false
                        )
                        Text(text = label, fontSize = 16.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Content
            Text("Content", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            NoteContentItem(
                value = note!!.content,
                onValueChange = {},
                readOnly = true,
                backgroundColor = priorityColor
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
