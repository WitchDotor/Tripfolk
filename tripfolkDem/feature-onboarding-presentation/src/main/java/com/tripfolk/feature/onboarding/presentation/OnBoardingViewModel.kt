package com.tripfolk.feature.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripfolk.feature.onboarding.domain.model.OnBoardingModel
import com.tripfolk.feature.onboarding.domain.repository.OnBoardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val repository: OnBoardingRepository
) : ViewModel() {

    var onBoardingScreenState: OnBoardingScreenState by mutableStateOf(OnBoardingScreenState.Loading)

    init {
        loadContent()
    }

    fun getOnboardingState() = repository.getOnBoardingState()
    fun setOnboardingOff() {
        onBoardingScreenState = OnBoardingScreenState.Loading
        viewModelScope.launch {
            repository.setOnboardingOff()
            onBoardingScreenState = OnBoardingScreenState.OnBoardingOff
        }
    }

    fun loadContent() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            onBoardingScreenState = OnBoardingScreenState.Error(throwable)
        }) {
            repository.getOnBoardingContent().collectLatest {
                onBoardingScreenState = OnBoardingScreenState.Content(it)
            }
        }
    }
}

sealed class OnBoardingScreenState {
    data object Loading : OnBoardingScreenState()
    data class Error(val error: Throwable) : OnBoardingScreenState()
    data class Content(val content: List<OnBoardingModel>) : OnBoardingScreenState()
    data object OnBoardingOff : OnBoardingScreenState()
}

