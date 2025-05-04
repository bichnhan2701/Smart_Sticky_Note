package com.example.smartstickynote.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartstickynote.R
import com.example.smartstickynote.navigation.BottomNavBar
import com.example.smartstickynote.navigation.Screen
import com.example.smartstickynote.ui.components.ActionButton

@Composable
fun UserProfileScreen(navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = { BottomNavBar(navController) { navController.navigate(Screen.Add.route) } }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF5F5F5))
        ) {
            ProfileHeader()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 155.dp)
                    .background(Color.White, shape = MaterialTheme.shapes.large)
                    .padding(top = 56.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileName()

                ProfileSettings(
                    isDarkMode = isDarkMode,
                    onDarkModeChange = {

                    },
                    onHelpClick = {  },
                    onAboutClick = {  }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ActionButton(
                    text = "Login with Google",
                    onClick = {

                    },
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White,
                    modifier = Modifier.fillMaxWidth(0.6f)
                )
            }

            ProfileAvatar()
        }
    }
}

@Composable
private fun ProfileHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color(0xFFFFE6F0))
            .padding(top = 32.dp, bottom = 72.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "My profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF313F42)
        )
    }
}

@Composable
private fun ProfileName() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 16.dp)
    ) {
        Text(
            text = "Bich Nhan",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF39544F)
        )
    }
}

@Composable
private fun ProfileSettings(
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    onHelpClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    Text(
        text = "Settings",
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color(0xFF313F42),
        modifier = Modifier
            .padding(start = 16.dp, bottom = 8.dp).fillMaxWidth(),
        textAlign = TextAlign.Start

    )
    SettingItem(
        icon = painterResource(id = R.drawable.dark_mode_night_moon_svgrepo_com),
        title = "Dark mode",
        trailing = {
            Switch(
                checked = isDarkMode,
                onCheckedChange = onDarkModeChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Blue,
                    uncheckedThumbColor = Color.Gray
                )
            )
        }
    )
    SettingItem(
        icon = painterResource(id = R.drawable.help_svgrepo_com),
        title = "Help",
        onClick = onHelpClick,
        trailing = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Next",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    )
    SettingItem(
        icon = painterResource(id = R.drawable.about_svgrepo_com),
        title = "About",
        onClick = onAboutClick,
        trailing = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "About",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    )
}

@Composable
private fun ProfileAvatar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 90.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .size(135.dp) // lớn hơn ảnh một chút để tạo khung viền
                .clip(CircleShape)
                .shadow(
                    elevation = 8.dp,
                    shape = CircleShape,
                    ambientColor = Color.Gray,
                    spotColor = Color.Gray
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.meo_loading),
                contentDescription = "Default Avatar",
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
        }
    }
}

@Composable
fun SettingItem(
    icon: androidx.compose.ui.graphics.painter.Painter,
    title: String,
    onClick: () -> Unit = {},
    trailing: @Composable () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color(0xFF9485AC)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color(0xFF313F42)
        )
        Spacer(modifier = Modifier.weight(1f))
        trailing()
    }
}