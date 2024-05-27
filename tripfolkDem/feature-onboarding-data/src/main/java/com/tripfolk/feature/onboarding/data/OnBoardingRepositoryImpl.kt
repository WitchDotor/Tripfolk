package com.tripfolk.feature.onboarding.data

import com.tripfolk.common.data.dataStore.AppDataStore
import com.tripfolk.feature.onboarding.domain.model.OnBoardingModel
import com.tripfolk.feature.onboarding.domain.repository.OnBoardingRepository
import com.tripfolk.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class OnBoardingRepositoryImpl @Inject constructor(
    private val datastore: AppDataStore,
    private val firestore: FirebaseFirestore,
    private val onBoardingMapper: OnBoardingMapper
) : OnBoardingRepository {
    override fun getOnBoardingState() = datastore.onBoarding.getValue()

    override fun getOnBoardingContent(): Flow<List<OnBoardingModel>> {
        return firestore.getOnboardingContent()
            .map { it.map(onBoardingMapper::mapFireBaseToDisplayModel) }
    }

    override suspend fun setOnboardingOff() = datastore.onBoarding.editValue(false)
}