package com.tripfolk.feature.profile.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripfolk.feature.darkmode.domain.DarkModeModel
import com.tripfolk.feature.darkmode.domain.DarkModeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DarkModeViewModel @Inject constructor(
    private val darkModeRepository: DarkModeRepository,
) : ViewModel() {

    fun getDarkModeState() = darkModeRepository.getDarkModeState()

    fun switchDarkMode(check: Boolean) {
        viewModelScope.launch {
            if (check) {
                darkModeRepository.switchDarkModeState(DarkModeModel.ENABLED)
            } else {
                darkModeRepository.switchDarkModeState(DarkModeModel.DISABLED)
            }
        }
    }
}
