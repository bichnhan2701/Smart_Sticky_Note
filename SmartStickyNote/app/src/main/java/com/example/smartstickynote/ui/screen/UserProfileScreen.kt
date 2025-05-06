package com.example.smartstickynote.ui.screen

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.smartstickynote.R
import com.example.smartstickynote.ui.components.BottomNavBar
import com.example.smartstickynote.navigation.Screen
import com.example.smartstickynote.ui.components.ActionButton
import com.example.smartstickynote.ui.viewmodel.NoteViewModel
import com.example.smartstickynote.ui.viewmodel.UserProfileViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
fun UserProfileScreen(
    navController: NavController,
    viewModel: UserProfileViewModel = hiltViewModel(),
    noteViewModel: NoteViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    // Google Sign-In launcher
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let {
                viewModel.signInWithGoogleIntent(
                    intent = it,
                    onSuccess = { Toast.makeText(context, "Login successfully!", Toast.LENGTH_SHORT).show() },
                    onError = { message -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }
                )
            }
        } else {
            Toast.makeText(context, "Sign-in cancelled", Toast.LENGTH_SHORT).show()
        }
    }
    // Google SignIn options
    val gso = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.google_web_client_id))
            .requestEmail()
            .build()
    }
    val googleSignInClient = remember { GoogleSignIn.getClient(context, gso) }

    val wasLoggedIn = remember { mutableStateOf(false) }
    LaunchedEffect(viewModel.isUserLoggedIn) {
        if (viewModel.isUserLoggedIn && !wasLoggedIn.value) {
            noteViewModel.restoreNotes()
            wasLoggedIn.value = true
        }
    }

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
                if (viewModel.isUserLoggedIn && viewModel.user != null) {
                    // Hiển thị tên người dùng
                    Text(
                        text = "${viewModel.user?.displayName}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF39544F),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Hiển thị email
                    Text(
                        text = viewModel.user?.email ?: "",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                } else {
                    // Nếu chưa đăng nhập, hiển thị như mặc định
                    ProfileName()
                }

                ProfileSettings(
                    isDarkMode = viewModel.isDarkMode,
                    onDarkModeChange = { viewModel.toggleDarkMode() },
                    onHelpClick = {},
                    onAboutClick = {}
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (viewModel.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                } else if (!viewModel.isUserLoggedIn) {
                    ActionButton(
                        text = "Login with Google",
                        onClick = {
                            val signInIntent = googleSignInClient.signInIntent
                            googleSignInLauncher.launch(signInIntent)
                        },
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.White,
                        modifier = Modifier.fillMaxWidth(0.6f)
                    )
                } else {
                    ActionButton(
                        text = "Logout",
                        onClick = {
                            viewModel.signOut()
                            Toast.makeText(context, "Logged out successfully!", Toast.LENGTH_SHORT).show()
                        },
                        containerColor = Color(0xFFE53935),
                        contentColor = Color.White,
                        modifier = Modifier.fillMaxWidth(0.6f)
                    )
                }
            }

            ProfileAvatar(viewModel.user?.photoUrl?.toString()) // Hiển thị ảnh đại diện của người dùng
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
            text = "No user signed in",
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
private fun ProfileAvatar(photoUrl: String? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 90.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .size(135.dp)
                .clip(CircleShape)
                .shadow(elevation = 8.dp, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (photoUrl != null) {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
            } else {
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