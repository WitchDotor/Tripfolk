package com.tripfolk.common.presentation

import androidx.navigation.NavHostController

abstract class BaseNavController(
    val navController: NavHostController,
) {
    protected val root get() = navController.graph.id

    fun navigateUp() = navController.navigateUp()
    fun popBackStack() = navController.popBackStack()
}