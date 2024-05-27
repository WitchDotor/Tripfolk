package com.tripfolk.firebase.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tripfolk.firebase.initializer.FirebaseInitializer
import com.tripfolk.firebase.initializer.FirebaseInitializerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseModule {

    @Binds
    abstract fun firebaseInitializer(impl: FirebaseInitializerImpl): FirebaseInitializer

    companion object {
        @Provides
        @Singleton
        fun firebaseAuth() = FirebaseAuth.getInstance()

        @Provides
        @Singleton
        fun firebaseFirestore() = FirebaseFirestore.getInstance()
    }
}