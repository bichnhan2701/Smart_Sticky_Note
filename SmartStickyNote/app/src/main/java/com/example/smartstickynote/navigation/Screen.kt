package com.example.smartstickynote.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Onboarding : Screen("onboarding")
    data object Home : Screen("home")
    data object Add : Screen("add")
    data object Detail : Screen("note_detail/{noteId}") {
        fun createId(noteId: String): String {
            return "note_detail/$noteId"
        }
    }
    data object Edit : Screen("edit_note/{noteId}") {
        fun createId(noteId: String): String {
            return "edit_note/$noteId"
        }
    }
}
