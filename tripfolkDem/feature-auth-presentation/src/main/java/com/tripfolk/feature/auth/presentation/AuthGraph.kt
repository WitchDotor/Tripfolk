package com.tripfolk.feature.auth.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tripfolk.feature.auth.presentation.enter.EnterScreen
import com.tripfolk.feature.auth.presentation.sign_in.SignInScreen
import com.tripfolk.feature.auth.presentation.sign_up.SignUpScreen

@Composable
fun AuthGraph(
    initialRoute: AuthRoutes,
    navigateToMain: () -> Unit,
) {
    val navController = rememberAuthNavController()
    NavHost(
        navController = navController.navController,
        startDestination = initialRoute.route
    ) {
        for (route in AuthRoutes.entries) {
            when (route) {
                AuthRoutes.Enter -> {
                    composable(AuthRoutes.Enter.route) {
                        EnterScreen(
                            navigateToSignIn = navController::toSignIn,
                            navigateToMain = navigateToMain,
                            navigateToSignUp = navController::toSignUp,
                        )
                    }
                }

                AuthRoutes.SignIn -> {
                    composable(AuthRoutes.SignIn.route) {
                        SignInScreen(
                            navigateToMain = navigateToMain,
                            navigateUp = navController::navigateUp,
                        )
                    }
                }

                AuthRoutes.SignUp -> {
                    composable(AuthRoutes.SignUp.route) {
                        SignUpScreen(
                            navigateToMain = navigateToMain,
                            navigateUp = navController::navigateUp,
                        )
                    }
                }
            }
        }
    }
}