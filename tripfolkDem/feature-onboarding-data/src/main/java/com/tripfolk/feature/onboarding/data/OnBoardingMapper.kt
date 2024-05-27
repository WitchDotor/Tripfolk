package com.tripfolk.feature.onboarding.data

import com.tripfolk.feature.onboarding.domain.model.OnBoardingModel
import com.tripfolk.firebase.model.FirebaseOnboardingContent
import javax.inject.Inject

class OnBoardingMapper @Inject constructor() {

    fun mapFireBaseToDisplayModel(firebaseModel: FirebaseOnboardingContent) =
        OnBoardingModel(
            title = firebaseModel.title ?: "",
            text = firebaseModel.text ?: "",
            imageUrl = firebaseModel.imageUrl ?: ""
        )
}