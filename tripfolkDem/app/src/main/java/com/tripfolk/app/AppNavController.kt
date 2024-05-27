package com.tripfolk.app

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.createChooser
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tripfolk.common.presentation.BaseNavController

class SingleNavController(navController: NavHostController) : BaseNavController(navController) {
    fun toMain() =
        navController.navigate(SingleRoutes.Main.route) { popUpTo(root) { inclusive = true } }

    fun toAuth() =
        navController.navigate(SingleRoutes.Auth.route) { popUpTo(root) { inclusive = true } }

    fun toPrivacyPolicy() = toBrowser(PRIVACY_POLICY_URL.toUri())
    fun toTermsAndConditions() = toBrowser(TERMS_AND_CONDITIONS_URL.toUri())

    private fun toBrowser(uri: Uri) =
        navController.context.startActivity(createChooser(Intent(ACTION_VIEW, uri), ""))

    companion object {
        private const val PRIVACY_POLICY_URL = "https://tripfolk-a4f0e.web.app/privacy_policy.html"
        private const val TERMS_AND_CONDITIONS_URL =
            "https://tripfolk-a4f0e.web.app/terms_and_conditions.html"
    }
}

@Composable
fun rememberSingleNavController() = rememberNavController().let(::SingleNavController)

enum class SingleRoutes(val route: String) {
    Main("main"),
    Auth("auth"),
    OnBoarding("onboarding")
}
