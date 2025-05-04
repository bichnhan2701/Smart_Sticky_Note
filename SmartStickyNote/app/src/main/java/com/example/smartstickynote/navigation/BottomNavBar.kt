package com.example.smartstickynote.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavController, onAddClick: () -> Unit) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route

    Box {
        // Bottom Navigation Bar
        Surface(
            color = Color.Transparent,
            shadowElevation = 10.dp,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            modifier = Modifier
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                    clip = false
                )
                .background(Color.White),
        ) {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 5.dp
            ) {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(Screen.Home.route) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = item.label,
                                modifier = Modifier.size(24.dp),
                                tint = Color(0xFF182E53)
                            )
                        },
                        label = if (item.label.isNotEmpty()) {
                            { Text(item.label, color = Color(0xFF182E53)) }
                        } else null,
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color(0xFFDAEAF7) // màu nền khi selected
                        )
                    )
                }
            }
        }

        // Floating Add Button
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-28).dp) // đẩy ra ngoài nav bar
                .size(62.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF555BFF), Color(0xFFF955FF))
                    ),
                    shape = CircleShape
                )
                .clickable { onAddClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White, modifier = Modifier.size(36.dp))
        }
    }
}
