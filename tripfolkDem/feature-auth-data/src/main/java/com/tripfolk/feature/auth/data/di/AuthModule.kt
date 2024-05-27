package com.tripfolk.feature.auth.data.di

import com.tripfolk.feature.auth.data.repository.AuthRepositoryImpl
import com.tripfolk.feature.auth.domain.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {

    @Binds
    fun authRepository(impl: AuthRepositoryImpl): AuthRepository
}