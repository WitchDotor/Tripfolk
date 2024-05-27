package com.tripfolk.feature.profile.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripfolk.feature.auth.domain.AuthRepository
import com.tripfolk.feature.profile.domain.model.ProfileModel
import com.tripfolk.feature.profile.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    var profileState: ProfileState by mutableStateOf(ProfileState.Loading)
    var isSignedOut: Boolean by mutableStateOf(false)

    init {
        loadProfile()
    }

    fun loadProfile() {
        profileState = ProfileState.Loading
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            profileState = ProfileState.Error(throwable)
        }) {
            profileRepository.getProfile().collectLatest {
                profileState = ProfileState.Content(it)
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
            isSignedOut = true
        }
    }

    fun deleteProfile() {
        viewModelScope.launch {
            profileRepository.deleteProfile()
            isSignedOut = true
        }
    }
}

sealed class ProfileState {
    data object Loading : ProfileState()
    data class Error(val error: Throwable) : ProfileState()
    data class Content(val profile: ProfileModel) : ProfileState()
}