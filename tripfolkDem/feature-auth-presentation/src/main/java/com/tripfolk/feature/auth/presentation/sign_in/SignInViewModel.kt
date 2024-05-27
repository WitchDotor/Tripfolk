package com.tripfolk.feature.auth.presentation.sign_in

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.tripfolk.feature.auth.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    googleClient: GoogleSignInClient,
) : ViewModel() {
    private val emailRegex = Patterns.EMAIL_ADDRESS

    var isSignedUp by mutableStateOf(false)
        private set

    var signUpError: Throwable? by mutableStateOf(null)
        private set

    var email: String by mutableStateOf("")
        private set

    var emailError: EmailError? by mutableStateOf(null)
        private set

    var password: String by mutableStateOf("")
        private set

    var passwordError: PasswordError? by mutableStateOf(null)
        private set

    val signInIntent = googleClient.signInIntent

    fun updateEmail(email: String) {
        this.email = email
        emailError = null
    }

    fun updatePassword(password: String) {
        this.password = password
        passwordError = null
    }

    fun signIn() {
        validateEmail()
        validatePassword()
        if (emailError != null || passwordError != null) return

        signUpError = null
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable -> signUpError = throwable },
        ) {
            authRepository.signInWithEmail(email, password)
            isSignedUp = true
        }
    }

    fun signInWithGoogle(token: String) {
        signUpError = null
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable -> signUpError = throwable },
        ) {
            authRepository.signInWithGoogle(token)
            isSignedUp = true
        }
    }

    private fun validateEmail(): Boolean {
        emailError = when {
            email.isEmpty() -> EmailError.REQUIRED
            email.matches(emailRegex.toRegex()).not() -> EmailError.INVALID
            else -> null
        }
        return emailError == null
    }

    private fun validatePassword(): Boolean {
        passwordError = when {
            password.length < PASSWORD_MIN_CHARACTERS -> PasswordError.REQUIRED
            else -> null
        }
        return passwordError == null
    }

    enum class EmailError {
        REQUIRED,
        INVALID,
    }

    enum class PasswordError {
        REQUIRED,
    }

    companion object {
        private const val PASSWORD_MIN_CHARACTERS = 6
    }
}