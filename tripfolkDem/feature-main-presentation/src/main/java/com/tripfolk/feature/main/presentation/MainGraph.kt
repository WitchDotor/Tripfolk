package com.tripfolk.feature.main.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tripfolk.feature.localisation.presentation.LocalisationScreen
import com.tripfolk.feature.profile.presentation.update_profile.CreateProfileScreen
import com.tripfolk.feature.profile.presentation.update_profile.EditProfileScreen

@Composable
fun MainGraph(
    initialRoute: MainRoutes,
    navigateToAuth: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    navigateToTermsAndConditions: () -> Unit,
) {
    val navController = rememberMainNavController()
    NavHost(
        navController = navController.navController,
        startDestination = initialRoute.route,
    ) {
        for (route in MainRoutes.entries) {
            when (route) {
                MainRoutes.Main -> composable(route.route) {
                    MainScreen(
                        navigateToEditProfile = navController::toEditProfile,
                        navigateToPrivacyPolicy = navigateToPrivacyPolicy,
                        navigateToTermsAndConditions = navigateToTermsAndConditions,
                        navigateToEnter = navigateToAuth,
                        navigateToLocalisation = navController::toLocalisation
                    )
                }

                MainRoutes.CreateProfile -> composable(route.route) {
                    CreateProfileScreen(
                        navigateNext = navController::toMain,
                    )
                }

                MainRoutes.EditProfile -> composable(route.route) {
                    EditProfileScreen(
                        navigateUp = navController::navigateUp,
                        navigateNext = navController::popBackStack,
                    )
                }

                MainRoutes.Localisation -> composable(route.route) {
                    LocalisationScreen(
                        navigateUp = navController::navigateUp
                    )
                }
            }
        }
    }
}