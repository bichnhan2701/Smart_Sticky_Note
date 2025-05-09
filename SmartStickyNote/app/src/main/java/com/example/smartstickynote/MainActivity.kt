package com.example.smartstickynote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.smartstickynote.domain.repository.PreferencesRepository
import com.example.smartstickynote.navigation.NavGraph
import com.example.smartstickynote.ui.theme.SmartStickyNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = PreferencesRepository(applicationContext)
        setContent {
            val navController = rememberNavController()
            NavGraph(navController = navController, preferences)
        }
    }
}