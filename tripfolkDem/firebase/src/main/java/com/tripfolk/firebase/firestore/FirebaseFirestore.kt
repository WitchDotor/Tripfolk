package com.tripfolk.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObject
import com.tripfolk.firebase.model.FirebaseOnboardingContent
import com.tripfolk.firebase.model.FirebasePointOfInterest
import com.tripfolk.firebase.model.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseFirestore @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) {
    suspend fun getUser(id: String): FirebaseUser? {
        return firebaseFirestore.user(id).get().await().toObject<FirebaseUser>()
    }

    suspend fun updateUser(id: String, data: FirebaseUser) {
        firebaseFirestore.user(id).set(data).await()
    }

    suspend fun deleteUser(id: String) {
        firebaseFirestore.user(id).delete().await()
    }

    fun getPointOfInterest(id: String): Flow<FirebasePointOfInterest> {
        return firebaseFirestore.pointOfInterest(id).snapshots().mapNotNull { it.toObject<FirebasePointOfInterest>() }
    }

    fun getPointsOfInterest(): Flow<List<FirebasePointOfInterest>> {
        return firebaseFirestore.pointsOfInterest().snapshots().map { it.map { it.toObject<FirebasePointOfInterest>() } }
            .onEach { it.map { it.copy(image = "https://picsum.photos/200/300") } }
    }

    fun getOnboardingContent(): Flow<List<FirebaseOnboardingContent>> {
        return firebaseFirestore.onBoardingContent().snapshots().map { it.map { it.toObject<FirebaseOnboardingContent>() } }
    }

    private fun FirebaseFirestore.user(id: String) = users().document(id)
    private fun FirebaseFirestore.users() = collection(USERS_COLLECTION)

    private fun FirebaseFirestore.pointOfInterest(id: String) = pointsOfInterest().document(id)
    private fun FirebaseFirestore.pointsOfInterest() = collection(POINTS_OF_INTEREST_COLLECTION)
    private fun FirebaseFirestore.onBoardingContent() = collection(ONBOARDING_CONTENT)

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val POINTS_OF_INTEREST_COLLECTION = "points_of_interest"
        private const val ONBOARDING_CONTENT = "onboarding_content"
    }
}