package com.tripfolk.firebase.initializer

import android.content.Context
import com.google.firebase.FirebaseApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseInitializerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : FirebaseInitializer {

    override fun initFirebase() {
        FirebaseApp.initializeApp(context)
    }
}