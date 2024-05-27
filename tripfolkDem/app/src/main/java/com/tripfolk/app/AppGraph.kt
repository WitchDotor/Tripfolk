package com.tripfolk.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tripfolk.feature.auth.presentation.AuthGraph
import com.tripfolk.feature.auth.presentation.AuthRoutes
import com.tripfolk.feature.main.presentation.MainGraph
import com.tripfolk.feature.main.presentation.MainRoutes
import com.tripfolk.feature.onboarding.presentation.OnBoardingScreen


@Composable
fun AppGraph(
    initialRoute: SingleRoutes,
) {
    val navController = rememberSingleNavController()
    NavHost(
        navController = navController.navController,
        startDestination = initialRoute.name,
    ) {
        for (route in SingleRoutes.entries) {
            when (route) {
                SingleRoutes.Main -> composable(route.route) {
                    MainGraph(
                        initialRoute = MainRoutes.Main,
                        navigateToAuth = navController::toAuth,
                        navigateToPrivacyPolicy = navController::toPrivacyPolicy,
                        navigateToTermsAndConditions = navController::toTermsAndConditions,
                    )
                }

                SingleRoutes.Auth -> composable(route.route) {
                    AuthGraph(
                        initialRoute = AuthRoutes.Enter,
                        navigateToMain = navController::toMain,
                    )
                }

                SingleRoutes.OnBoarding -> composable(route.route) {
                    OnBoardingScreen(
                        navigate = { navController.toAuth() }
                    )
                }
            }
        }
    }
}