package com.example.smartstickynote.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartstickynote.R
import com.example.smartstickynote.domain.model.Category
import com.example.smartstickynote.ui.components.ActionButton
import com.example.smartstickynote.ui.components.ColorPicker
import com.example.smartstickynote.ui.viewmodel.CategoryViewModel
import com.example.smartstickynote.ui.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    navController: NavController
) {
    val noteViewModel: NoteViewModel = hiltViewModel()

    val categories by viewModel.categories.collectAsState()
    val notes by noteViewModel.notes.collectAsState() // giả sử bạn đã có danh sách ghi chú để đếm
    var text by remember { mutableStateOf("") }
    val colorOptions = listOf(
        0xFFE57373.toInt(), // Đỏ nhạt
        0xFF81C784.toInt(), // Xanh lá nhạt
        0xFF64B5F6.toInt(), // Xanh dương nhạt
        0xFFFFB74D.toInt(), // Cam nhạt
        0xFFBA68C8.toInt(), // Tím nhạt
        0xFFFF8A65.toInt(), // Cam san hô
        0xFF4DB6AC.toInt(), // Xanh ngọc
        0xFFA1887F.toInt(), // Nâu xám
        0xFF7986CB.toInt(), // Tím xanh
        0xFFDCE775.toInt(), // Vàng nhạt
        0xFF90A4AE.toInt(), // Xám xanh
        0xFFF06292.toInt(), // Hồng rực
        0xFF9575CD.toInt(), // Tím pastel
        0xFFFFD54F.toInt(), // Vàng sáng
        0xFF4FC3F7.toInt()  // Xanh da trời nhạt
    )
    var selectedColor by remember { mutableIntStateOf(colorOptions.first()) }

    var editingCategory by remember { mutableStateOf<Category?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh mục ghi chú", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(R.drawable.back_svgrepo_com),
                            contentDescription = "Quay lại",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues).padding(16.dp)) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Tên danh mục") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Text("Chọn màu:", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            ColorPicker(
                colorOptions = colorOptions,
                selectedColor = selectedColor,
                onColorSelected = { selectedColor = it }
            )


            Spacer(Modifier.height(16.dp))

            ActionButton(
                text = if (editingCategory != null) "Cập nhật danh mục" else "Thêm danh mục",
                onClick = {
                    if (text.isNotBlank()) {
                        if (editingCategory == null) {
                            // Thêm mới
                            viewModel.addCategory(text, selectedColor)
                        } else {
                            // Cập nhật danh mục
                            val updatedCategory = editingCategory!!.copy(title = text, color = selectedColor)
                            viewModel.updateCategory(updatedCategory)
                            editingCategory = null
                        }
                        text = ""
                    }
                },
                containerColor = Color(0xFF95DBF1),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            LazyColumn {
                items(categories) { category ->
                    val count = notes.count { it.categoryId == category.id }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clickable {
                                // Đưa dữ liệu danh mục lên UI để chỉnh sửa
                                editingCategory = category
                                text = category.title
                                selectedColor = category.color
                            },
                        colors = CardDefaults.cardColors(containerColor = Color(category.color))
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = category.title,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "$count ghi chú",
                                    fontSize = 14.sp,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                            }
                            IconButton(onClick = { viewModel.deleteCategory(category) }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.delete_2_svgrepo_com),
                                    contentDescription = "Xóa",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
