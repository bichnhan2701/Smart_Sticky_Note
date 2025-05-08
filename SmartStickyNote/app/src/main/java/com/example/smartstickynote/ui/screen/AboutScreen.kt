package com.example.smartstickynote.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun AboutScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Giới Thiệu",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Smart Sticky Note là ứng dụng ghi chú nhanh, giúp bạn quản lý công việc và ý tưởng mọi lúc mọi nơi.")

        Spacer(modifier = Modifier.height(16.dp))

        Text("✨ Tính năng chính:", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("• Ghi chú nhanh theo mức mức độ ưu tiên")
        Text("• Chỉnh sửa ghi chú")

        Spacer(modifier = Modifier.height(24.dp))

        Text("📦 Phiên bản: 1.0.0")
        Text("👨‍💻 Phát triển bởi: SmartNote Team")
    }
}
