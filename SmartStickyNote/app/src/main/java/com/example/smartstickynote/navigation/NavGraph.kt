package com.example.smartstickynote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.smartstickynote.domain.repository.PreferencesRepository
import com.example.smartstickynote.ui.screen.AddNewNoteScreen
import com.example.smartstickynote.ui.screen.EditNoteScreen
import com.example.smartstickynote.ui.screen.FoldersScreen
import com.example.smartstickynote.ui.screen.HomeScreen
import com.example.smartstickynote.ui.screen.NoteDetailScreen
import com.example.smartstickynote.ui.screen.OnboardingScreen
import com.example.smartstickynote.ui.screen.SplashScreen
import com.example.smartstickynote.ui.screen.TagsScreen
import com.example.smartstickynote.ui.screen.UserProfileScreen

@Composable
fun NavGraph(navController: NavHostController, preferences: PreferencesRepository) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController, preferences)
        }
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onSkip = {
                    preferences.setSeenOnboarding()
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                },
                onDone = {
                    preferences.setSeenOnboarding()
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(BottomNavItem.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Add.route) {
            AddNewNoteScreen(navController)
        }
        composable(BottomNavItem.Profile.route) {
            UserProfileScreen(navController)
        }
        composable(Screen.Folders.route) {
            FoldersScreen(navController)
        }
        composable(Screen.Tags.route) {
            TagsScreen(navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("noteId") { type = NavType.StringType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: return@composable
            NoteDetailScreen(navController = navController, noteId = noteId)
        }
        composable(
            route = Screen.Edit.route,
            arguments = listOf(navArgument("noteId") { type = NavType.StringType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: return@composable
            EditNoteScreen(navController = navController, noteId = noteId)
        }
    }
}