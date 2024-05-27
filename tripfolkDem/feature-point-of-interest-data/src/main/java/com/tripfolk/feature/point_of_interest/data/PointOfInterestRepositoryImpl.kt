package com.tripfolk.feature.point_of_interest.data

import com.tripfolk.feature.point_of_interest.domain.model.PointOfInterestModel
import com.tripfolk.feature.point_of_interest.domain.repository.PointOfInterestRepository
import com.tripfolk.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PointOfInterestRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val mapper: PointOfInterestMapper,
) : PointOfInterestRepository {

    override fun getPointOfInterest(id: String): Flow<PointOfInterestModel> {
        return firestore.getPointOfInterest(id).map(mapper::mapPointOfInterest)
    }

    override fun getPointsOfInterest(): Flow<List<PointOfInterestModel>> {
        return firestore.getPointsOfInterest().map { it.map(mapper::mapPointOfInterest) }
    }
}