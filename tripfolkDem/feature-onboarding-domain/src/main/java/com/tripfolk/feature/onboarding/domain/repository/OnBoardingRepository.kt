package com.tripfolk.feature.onboarding.domain.repository

import com.tripfolk.feature.onboarding.domain.model.OnBoardingModel
import kotlinx.coroutines.flow.Flow

interface OnBoardingRepository {
    fun getOnBoardingContent(): Flow<List<OnBoardingModel>>
    fun getOnBoardingState(): Boolean
    suspend fun setOnboardingOff()
}