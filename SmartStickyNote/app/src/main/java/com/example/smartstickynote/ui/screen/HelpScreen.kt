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
fun HelpScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Hỗ Trợ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Nếu bạn gặp sự cố khi sử dụng Smart Sticky Note hoặc có góp ý, vui lòng liên hệ:")

        Spacer(modifier = Modifier.height(16.dp))

        Text("📧 Email hỗ trợ:", fontWeight = FontWeight.SemiBold)
        Text("2251120137@ut.edu.vn")
        Text("2251120173@ut.edu.vn")
        
    }
}
