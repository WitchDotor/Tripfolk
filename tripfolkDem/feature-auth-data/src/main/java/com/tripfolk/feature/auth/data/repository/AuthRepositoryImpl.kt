package com.tripfolk.feature.auth.data.repository

import com.tripfolk.feature.auth.data.storage.AuthLocalStorage
import com.tripfolk.feature.auth.domain.AuthRepository
import com.tripfolk.firebase.auth.FirebaseAuth
import com.tripfolk.firebase.firestore.FirebaseFirestore
import com.tripfolk.firebase.model.FirebaseUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val localStorage: AuthLocalStorage,
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
) : AuthRepository {

    override fun isAuthorized() = firebaseAuth.isAuthorized()

    override suspend fun signUpWithEmail(email: String, password: String) {
        try {
            val userId = firebaseAuth.signUpWithEmail(email, password)
            firebaseFirestore.updateUser(userId, FirebaseUser(id = userId, isGuest = false))
        } catch (e: Exception) {
            // TODO add exception handling
            throw e
        }
    }

    override suspend fun signInWithEmail(email: String, password: String) {
        try {
            firebaseAuth.signInWithEmail(email, password)
        } catch (e: Exception) {
            // TODO add exception handling
            throw e
        }
    }

    override suspend fun signInWithGoogle(token: String) {
        try {
            firebaseAuth.signInWithGoogle(token)
        } catch (e: Exception) {
            // TODO add exception handling
            throw e
        }
    }

    override suspend fun signOut() {
        try {
            firebaseAuth.signOut()
        } catch (e: Exception) {
            // TODO add exception handling
            throw e
        }
    }

    override suspend fun signInAsGuest() {
        try {
            val userId = firebaseAuth.signInAnonymously()
            firebaseFirestore.updateUser(userId, FirebaseUser(id = userId, isGuest = true))
        } catch (e: Exception) {
            // TODO add exception handling
            throw e
        }
    }
}