package com.tripfolk.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuth @Inject constructor(
    private val auth: FirebaseAuth,
) {
    val profileId get() = auth.currentUser?.uid ?: error("User is not logged in")

    fun isAuthorized() = auth.currentUser != null

    suspend fun signInAnonymously(): String {
        val result = auth.signInAnonymously().await()
        return result.user?.uid ?: error("User is not signed in")
    }

    suspend fun signUpWithEmail(email: String, password: String): String {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        return result.user?.uid ?: error("User is not signed up")
    }

    suspend fun signInWithEmail(email: String, password: String): String {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        return result.user?.uid ?: error("User is not signed in")
    }

    suspend fun signInWithGoogle(token: String): String {
        val firebaseCredential = GoogleAuthProvider.getCredential(token, null)
        val result = auth.signInWithCredential(firebaseCredential).await()
        return result.user?.uid ?: error("User is not signed in")
    }

    fun signOut() = auth.signOut()

    suspend fun deleteAccount() {
        auth.currentUser?.delete()?.await()
    }
}