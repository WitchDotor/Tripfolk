package com.tripfolk.feature.point_of_interest.data

import com.tripfolk.feature.point_of_interest.domain.repository.PointOfInterestRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PointOfInterestModule {
    @Binds
    fun pointOfInterestRepository(impl: PointOfInterestRepositoryImpl): PointOfInterestRepository
}