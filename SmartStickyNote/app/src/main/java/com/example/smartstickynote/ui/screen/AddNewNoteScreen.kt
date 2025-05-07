package com.example.smartstickynote.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.navigation.NavController
import com.example.smartstickynote.R
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.domain.model.Tag
import com.example.smartstickynote.ui.components.AutoCategoryChips
import com.example.smartstickynote.ui.components.NoteContentItem
import com.example.smartstickynote.ui.components.SimpleFolderData
import com.example.smartstickynote.ui.components.SimpleFolderSelector
import com.example.smartstickynote.ui.components.TagList
import com.example.smartstickynote.ui.components.TagSelectionDialog
import com.example.smartstickynote.ui.components.TagsRow
import com.example.smartstickynote.ui.viewmodel.NoteViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.UUID

@Composable
fun AddNewNoteScreen(
    navController: NavController,
    viewModel: NoteViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf("High") }
    var selectedSimpleFolder by remember { mutableStateOf<SimpleFolderData?>(null) }
    var selectedTags by remember { mutableStateOf<List<Tag>>(emptyList()) }
    var showTagDialog by remember { mutableStateOf(false) }
    
    val folders by viewModel.folders.collectAsState(initial = emptyList())
    
    val simpleFolders = folders.map { folder ->
        SimpleFolderData(
            id = folder.id,
            name = folder.name,
            color = folder.color,
            noteCount = folder.noteCount
        )
    }
    
    val tags by viewModel.tags.collectAsState(initial = emptyList())
    
    val autoCategories = remember(title, content) {
        if (title.isBlank() && content.isBlank()) emptyList()
        else {
            val seed = (title + content).hashCode()
            val categories = listOf("công việc", "học tập", "cá nhân", "sức khỏe", "tài chính")
            val index1 = Math.abs(seed % categories.size)
            val index2 = Math.abs((seed / 10) % categories.size)
            if (index1 != index2) listOf(categories[index1], categories[index2])
            else listOf(categories[index1])
        }
    }
    
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
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
                        createdAt = System.currentTimeMillis(),
                        updatedAt = System.currentTimeMillis(),
                        folderId = selectedSimpleFolder?.id,
                        tags = selectedTags,
                        autoCategories = autoCategories
                    )
                    viewModel.addNote(newNote)
                    navController.popBackStack()
                }
            }) {
                Icon(
                    painter = painterResource(R.drawable.mingcute_save_line),
                    contentDescription = "Save",
                    tint = Color(0xFF4CAF50)
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

        Spacer(modifier = Modifier.height(16.dp))
        
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
        
        if (showTagDialog) {
            TagSelectionDialog(
                availableTags = tags,
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

        Spacer(modifier = Modifier.height(16.dp))

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
        
        if (autoCategories.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))
            
            AutoCategoryChips(
                categories = autoCategories,
                onCategoryClick = { /* Có thể thêm xử lý khi người dùng nhấp vào */ }
            )
        }
        
        Spacer(modifier = Modifier.height(50.dp))
    }
}