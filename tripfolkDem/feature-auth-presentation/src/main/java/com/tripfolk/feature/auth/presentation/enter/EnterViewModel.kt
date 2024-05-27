package com.tripfolk.feature.auth.presentation.enter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripfolk.feature.auth.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    var isSignedUp by mutableStateOf(false)
        private set
    var signUpError: Throwable? by mutableStateOf(null)
        private set

    fun signInAsGuest() {
        signUpError = null
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable -> signUpError = throwable },
        ) {
            authRepository.signInAsGuest()
            isSignedUp = true
        }
    }
}