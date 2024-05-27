package com.tripfolk.feature.auth.domain

import javax.inject.Singleton

@Singleton
interface AuthRepository {
    fun isAuthorized(): Boolean
    suspend fun signUpWithEmail(email: String, password: String)
    suspend fun signInWithEmail(email: String, password: String)
    suspend fun signInWithGoogle(token: String)
    suspend fun signInAsGuest()
    suspend fun signOut()
}