package com.tripfolk.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import com.tripfolk.feature.darkmode.domain.DarkModeModel
import com.tripfolk.feature.onboarding.presentation.OnBoardingViewModel
import com.tripfolk.feature.profile.presentation.profile.DarkModeViewModel
import com.tripfolk.ui_kit.theme.UiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {

    private val appViewModel by viewModels<AppViewModel>()
    private val onBoardingViewModel by viewModels<OnBoardingViewModel>()
    private val darkModeViewModel by viewModels<DarkModeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val darkMode =
                when (darkModeViewModel.getDarkModeState().collectAsState(initial = DarkModeModel.AS_SYSTEM).value) {
                    DarkModeModel.ENABLED -> true
                    DarkModeModel.DISABLED -> false
                    DarkModeModel.AS_SYSTEM -> isSystemInDarkTheme()
                }

            UiTheme(
                darkTheme = darkMode,
            ) {
                AppGraph(
                    initialRoute = if (onBoardingViewModel.getOnboardingState()) {
                        SingleRoutes.OnBoarding
                    } else {
                        when (appViewModel.isAuthored) {
                            true -> SingleRoutes.Main
                            false -> SingleRoutes.Auth
                        }
                    }
                )
            }
        }
    }
}