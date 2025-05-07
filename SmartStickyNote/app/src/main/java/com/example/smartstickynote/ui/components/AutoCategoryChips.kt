package com.example.smartstickynote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoCategoryChips(
    categories: List<String>,
    onCategoryClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Danh mục tự động",
            style = MaterialTheme.typography.titleMedium
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                SuggestionChip(
                    onClick = { onCategoryClick(category) },
                    shape = RoundedCornerShape(16.dp),
                    label = { Text(category) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color(0xFF4CAF50) // Màu xanh lá
                        )
                    },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChip(
    category: String,
    onClick: () -> Unit
) {
    // Màu ngẫu nhiên nhưng ổn định cho mỗi danh mục
    val seed = category.hashCode()
    val r = ((seed and 0xFF0000) shr 16) / 255f
    val g = ((seed and 0x00FF00) shr 8) / 255f
    val b = (seed and 0x0000FF) / 255f
    val categoryColor = Color(r, g, b, 0.2f)
    
    SuggestionChip(
        onClick = onClick,
        label = { Text(category) },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = categoryColor,
            labelColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Composable
fun AutoCategorySection(
    autoCategories: List<String>,
    selectedCategory: String? = null,
    onCategorySelected: (String?) -> Unit
) {
    if (autoCategories.isEmpty()) return
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = "Phân loại tự động",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            "Ghi chú này được tự động phân loại dựa trên nội dung.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        val scrollState = rememberScrollState()
        
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Tùy chọn "Tất cả"
            FilterChip(
                selected = selectedCategory == null,
                onClick = { onCategorySelected(null) },
                label = { Text("Tất cả") }
            )
            
            // Các danh mục tự động
            autoCategories.forEach { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { onCategorySelected(category) },
                    label = { Text(category) }
                )
            }
        }
    }
} 