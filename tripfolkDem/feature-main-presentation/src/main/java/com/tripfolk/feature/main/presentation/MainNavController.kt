package com.tripfolk.feature.main.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tripfolk.common.presentation.BaseNavController

class MainNavController(navController: NavHostController) : BaseNavController(navController) {
    fun toMain() = navController.navigate(MainRoutes.Main.route) { popUpTo(root) { inclusive = true } }

    fun toCreateProfile() = navController.navigate(MainRoutes.EditProfile.route) { popUpTo(root) { inclusive = true } }

    fun toEditProfile() = navController.navigate(MainRoutes.EditProfile.route)
    fun toLocalisation() = navController.navigate(MainRoutes.Localisation.route)
}

@Composable
fun rememberMainNavController() = rememberNavController().let(::MainNavController)

enum class MainRoutes(val route: String) {
    Main("main"),
    CreateProfile("create_profile"),
    EditProfile("edit_profile"),
    Localisation("localisation")
}
