package com.tripfolk.app

import androidx.lifecycle.ViewModel
import com.tripfolk.feature.auth.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    authRepository: AuthRepository,
) : ViewModel() {

    val isAuthored = authRepository.isAuthorized()
}