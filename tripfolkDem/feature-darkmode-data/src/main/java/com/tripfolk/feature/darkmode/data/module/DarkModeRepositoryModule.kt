package com.tripfolk.feature.darkmode.data.module


import com.tripfolk.feature.darkmode.domain.DarkModeRepository
import com.tripfolk.feature.darkmode.data.DarkModeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DarkModeRepositoryModule {
    @Binds
    fun provideDarkModeRepository(darkModeRepositoryImpl: DarkModeRepositoryImpl): DarkModeRepository
}