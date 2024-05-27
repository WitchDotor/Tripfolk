package com.tripfolk.feature.point_of_interest.domain.repository

import com.tripfolk.feature.point_of_interest.domain.model.PointOfInterestModel
import kotlinx.coroutines.flow.Flow

interface PointOfInterestRepository {
    fun getPointOfInterest(id: String): Flow<PointOfInterestModel>
    fun getPointsOfInterest(): Flow<List<PointOfInterestModel>>
}