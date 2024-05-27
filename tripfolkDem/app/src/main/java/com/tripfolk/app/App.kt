package com.tripfolk.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.tripfolk.feature.darkmode.domain.DarkModeModel
import com.tripfolk.feature.darkmode.domain.DarkModeRepository
import com.tripfolk.feature.localisation.domain.LocalisationRepository
import com.tripfolk.firebase.initializer.FirebaseInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var firebaseInitializer: FirebaseInitializer

    @Inject
    lateinit var localisationRepository: LocalisationRepository
    override fun onCreate() {
        super.onCreate()

        initFirebase()

        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(
                localisationRepository.getCurrentLanguage().code
            )
        )

    }

    private fun initFirebase() {
        firebaseInitializer.initFirebase()
    }
}