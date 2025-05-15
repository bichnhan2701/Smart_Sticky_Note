package com.example.smartstickynote.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    data object Home : BottomNavItem("home", Icons.Outlined.Home, "Trang chủ")
    data object Profile : BottomNavItem("profile", Icons.Outlined.Person, "Hồ sơ")
}