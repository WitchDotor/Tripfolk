package com.tripfolk.feature.profile.data.di

import com.tripfolk.feature.profile.data.ProfileRepositoryImpl
import com.tripfolk.feature.profile.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProfileModule {
    @Binds
    fun profileRepository(impl: ProfileRepositoryImpl): ProfileRepository
}