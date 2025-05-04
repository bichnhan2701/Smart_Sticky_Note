package com.example.smartstickynote.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.example.smartstickynote.R
import com.example.smartstickynote.ui.components.NoteContentItem
import com.example.smartstickynote.ui.viewmodel.NoteViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartstickynote.domain.model.Note
import java.util.UUID

@Composable
fun AddNewNoteScreen(
    navController: NavController,
    viewModel: NoteViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf("High") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                if (title.isNotBlank() || content.isNotBlank()) {
                    val newNote = Note(
                        id = UUID.randomUUID().toString(),
                        title = title,
                        content = content,
                        priorityRate = selectedPriority,
                        isFavorite = false,
                        isPin = false,
                        createdAt = System.currentTimeMillis()
                    )
                    viewModel.addNote(newNote)
                    navController.popBackStack() // quay lại trang Home
                }
            }) {
                Icon(
                    painter = painterResource(R.drawable.mingcute_save_line),
                    contentDescription = "Save",
                    tint = Color(0xFF4CAF50)
                )
            }

            IconButton(onClick = {navController.popBackStack()}) {
                Icon(
                    painter = painterResource(R.drawable.close),
                    contentDescription = "Cancel",
                    tint = Color(0xFFF44336)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Title",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            placeholder = { Text("Add your title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF9C55FF),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Choose the priority rate",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
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
                    Text(
                        label,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Content",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        NoteContentItem (
            value = content,
            onValueChange = { content = it }
        )
    }
}