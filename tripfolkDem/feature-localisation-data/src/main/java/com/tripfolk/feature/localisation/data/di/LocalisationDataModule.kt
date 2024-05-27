package com.tripfolk.feature.localisation.data.di

import com.tripfolk.feature.localisation.data.LocalisationRepositoryImpl
import com.tripfolk.feature.localisation.domain.LocalisationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalisationDataModule {

    @Binds
    fun localisationRepository(impl: LocalisationRepositoryImpl): LocalisationRepository
}