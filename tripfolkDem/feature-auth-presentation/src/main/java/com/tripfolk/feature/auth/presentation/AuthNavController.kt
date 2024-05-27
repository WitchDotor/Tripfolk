package com.tripfolk.feature.auth.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tripfolk.common.presentation.BaseNavController

class AuthNavController(navController: NavHostController) : BaseNavController(navController) {
    fun toEnter() = navController.navigate(AuthRoutes.Enter.route) { popUpTo(root) { inclusive = true } }
    fun toSignIn() = navController.navigate(AuthRoutes.SignIn.route)
    fun toSignUp() = navController.navigate(AuthRoutes.SignUp.route)
}

enum class AuthRoutes(val route: String) {
    Enter("enter"),
    SignIn("sign_in"),
    SignUp("sign_up"),
}

@Composable
fun rememberAuthNavController() = rememberNavController().let(::AuthNavController)