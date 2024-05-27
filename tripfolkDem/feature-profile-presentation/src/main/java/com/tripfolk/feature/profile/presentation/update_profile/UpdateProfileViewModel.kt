package com.tripfolk.feature.profile.presentation.update_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripfolk.feature.profile.domain.model.UpdateProfileModel
import com.tripfolk.feature.profile.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
) : ViewModel() {

    var loading: Boolean by mutableStateOf(true)
        private set
    var name: String? by mutableStateOf(null)
        private set
    var nameError: NameError? by mutableStateOf(null)
        private set
    var dateOfBirth: Date? by mutableStateOf(null)
        private set
    var dateOfBirthError: DateOfBirthError? by mutableStateOf(null)
        private set
    var updateState: UpdateState by mutableStateOf(UpdateState.Idle)

    init {
        loadProfile()
    }

    fun updateName(name: String) {
        this.name = name
        validateName()
    }

    fun updateDateOfBirth(dateOfBirth: Date?) {
        this.dateOfBirth = dateOfBirth
        validateDateOfBirth()
    }

    fun save() {
        if (validate()) updateProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            loading = true
            val profile = profileRepository.getProfile().first()
            name = profile.name
            dateOfBirth = profile.dateOfBirth
            loading = false
        }
    }

    private fun validate(): Boolean = listOf(
        validateName(),
        validateDateOfBirth(),
    ).all { it }

    private fun validateName(): Boolean {
        val name = name
        nameError = when {
            name.isNullOrBlank() -> NameError.Required
            else -> null
        }
        return nameError == null
    }

    private fun validateDateOfBirth(): Boolean {
        val dateOfBirth = dateOfBirth
        dateOfBirthError = when {
            dateOfBirth == null -> DateOfBirthError.Required
            dateOfBirth.after(Date()) -> DateOfBirthError.Invalid
            else -> null
        }
        return dateOfBirthError == null
    }

    private fun updateProfile() {
        viewModelScope.launch {
            updateState = UpdateState.Processing
            profileRepository.updateProfile(
                UpdateProfileModel(
                    name = name?.trim(),
                    dateOfBirth = dateOfBirth,
                )
            )
            updateState = UpdateState.Completed
        }
    }
}

enum class NameError {
    Required,
}

enum class DateOfBirthError {
    Required,
    Invalid,
}

enum class UpdateState {
    Idle,
    Processing,
    Completed,
}