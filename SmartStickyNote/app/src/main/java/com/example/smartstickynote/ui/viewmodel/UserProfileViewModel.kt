package com.example.smartstickynote.ui.viewmodel

import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartstickynote.domain.repository.AuthRepository
import com.example.smartstickynote.domain.usecase.SignInWithGoogleUseCase
import com.example.smartstickynote.domain.usecase.SignOutUseCase
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val googleSignInClient: GoogleSignInClient,
    authRepository: AuthRepository
) : ViewModel() {

    var isDarkMode by mutableStateOf(false)
        private set
    var isUserLoggedIn by mutableStateOf(authRepository.getCurrentUser() != null)
        private set
    var user by mutableStateOf(authRepository.getCurrentUser())
        private set
    var isLoading by mutableStateOf(false)
        private set

    fun signInWithGoogleIntent(
        intent: Intent,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        viewModelScope.launch {
            isLoading = true
            try {
                val account = task.getResult(ApiException::class.java)
                val result = signInWithGoogleUseCase(account.idToken!!)
                if (result.isSuccess) {
                    user = result.getOrNull()
                    isUserLoggedIn = true
                    onSuccess()
                } else {
                    onError(result.exceptionOrNull()?.message ?: "Login failed.")
                }
            } catch (e: ApiException) {
                onError("Google Sign-In error: ${e.localizedMessage}")
            } finally {
                isLoading = false
            }
        }
    }

    fun signOut() {
        signOutUseCase()
        googleSignInClient.signOut()
        user = null
        isUserLoggedIn = false
    }

    fun toggleDarkMode() {
        isDarkMode = !isDarkMode
    }
}

