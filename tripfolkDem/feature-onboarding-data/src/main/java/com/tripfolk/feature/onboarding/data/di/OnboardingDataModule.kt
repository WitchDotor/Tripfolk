package com.tripfolk.feature.onboarding.data.di

import com.tripfolk.feature.onboarding.data.OnBoardingRepositoryImpl
import com.tripfolk.feature.onboarding.domain.repository.OnBoardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface OnboardingDataModule {
    @Binds
    fun onBoardingRepository(impl: OnBoardingRepositoryImpl): OnBoardingRepository
}