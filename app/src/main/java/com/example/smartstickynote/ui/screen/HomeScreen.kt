package com.example.smartstickynote.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartstickynote.navigation.BottomNavBar
import com.example.smartstickynote.navigation.Screen
import com.example.smartstickynote.ui.components.FilterChip
import com.example.smartstickynote.ui.components.GradientText
import com.example.smartstickynote.ui.components.NoteItem
import com.example.smartstickynote.R
import com.example.smartstickynote.domain.model.Filter
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.ui.components.DeleteNoteDialog
import com.example.smartstickynote.ui.viewmodel.NoteViewModel
import com.example.smartstickynote.utils.getColorByPriority

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val notes by viewModel.notes.collectAsState()
    val filter by viewModel.setFilter.collectAsState()
    var noteToDelete by remember { mutableStateOf<Note?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = { BottomNavBar(navController) { navController.navigate(Screen.Add.route) } }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GradientText("Sticky Note", fontSize = 30.sp)
                Icon(Icons.Default.Search, contentDescription = null)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Filter Chips
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter_xmark_svgrepo_com),
                    contentDescription = "Clear Filter",
                    modifier = Modifier.clickable { viewModel.setFilter(Filter.NONE) }
                )
                Spacer(modifier = Modifier.width(10.dp))
                FilterChip(
                    text = "High",
                    selected = filter == Filter.HIGH,
                    onClick = { viewModel.setFilter(Filter.HIGH) }
                )
                FilterChip(
                    text = "Medium",
                    selected = filter == Filter.MEDIUM,
                    onClick = { viewModel.setFilter(Filter.MEDIUM) }
                )
                FilterChip(
                    text = "Low",
                    selected = filter == Filter.LOW,
                    onClick = { viewModel.setFilter(Filter.LOW) }
                )
                FilterChip(
                    text = "Favorite",
                    selected = filter == Filter.FAVORITE,
                    onClick = { viewModel.setFilter(Filter.FAVORITE) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Notes Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(notes) { note ->
                    NoteItem(
                        title = note.title,
                        content = note.content,
                        color = getColorByPriority(note.priorityRate),
                        isPinned = note.isPin,
                        isFavorite = note.isFavorite,
                        onPin = {},
                        onDelete = {
                            noteToDelete = note
                            showDeleteDialog = true
                        },
                        onEdit = {},
                        onTitleClick = {
                            navController.navigate(Screen.Detail.createId(note.id))
                        },
                        onFavoriteClick = {
                            viewModel.toggleFavorite(note)
                        }
                    )
                }
            }
        }

        // Delete dialog
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
                    },
                    onDismiss = {
                        showDeleteDialog = false
                        noteToDelete = null
                    }
                )
            }
        }
    }
}