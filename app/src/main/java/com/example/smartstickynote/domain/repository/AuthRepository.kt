package com.example.smartstickynote.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser>
    fun signOut()
    fun getCurrentUser(): FirebaseUser?
}
