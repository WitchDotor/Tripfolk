package com.tripfolk.feature.auth.presentation.sign_up

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.tripfolk.feature.auth.presentation.BuildConfig
import com.tripfolk.feature.auth.presentation.R
import com.tripfolk.ui_kit.component.appbar.UiAppBar
import com.tripfolk.ui_kit.component.button.primary.UiPrimaryButton
import com.tripfolk.ui_kit.component.button.social_media.SocialMediaButton
import com.tripfolk.ui_kit.component.field.UiTextField
import com.tripfolk.ui_kit.component.screen.UiScreen
import com.tripfolk.ui_kit.theme.UiTheme

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    navigateToMain: () -> Unit,
    navigateToFacebook: () -> Unit = {},
    navigateToApple: () -> Unit = {},
) {
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.isSignedUp.let { LaunchedEffect(it) { if (it) navigateToMain() } }
    viewModel.signUpError?.let { LaunchedEffect(it) { snackbarHostState.showSnackbar(it.message ?: it.toString()) } }

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(BuildConfig.AUTH_WEB_CLIENT_KEY)
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(LocalContext.current as Activity, gso)

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)
            account?.idToken?.let { viewModel.signInWithGoogle(it) } ?: error(R.string.google_sign_in_error)
        }
    }

    Layout(
        email = viewModel.email,
        password = viewModel.password,
        snackbarHostState = snackbarHostState,
        emailError = when (viewModel.emailError) {
            SignUpViewModel.EmailError.INVALID -> stringResource(id = R.string.sign_up_email_error_invalid)
            SignUpViewModel.EmailError.REQUIRED -> stringResource(id = R.string.sign_up_email_error_required)
            null -> ""
        },
        passwordError = when (viewModel.passwordError) {
            SignUpViewModel.PasswordError.REQUIRED -> stringResource(id = R.string.sign_up_password_error_required)
            null -> ""
        },
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onNavigateUp = navigateUp,
        onCreateClick = {
            viewModel.signUp()
        },
        onFacebookClick = navigateToFacebook,
        onGoogleClick = {
            onGoogleClick(googleSignInClient, googleSignInLauncher)
        },
        onAppleClick = navigateToApple,
    )
}

@Composable
private fun Layout(
    email: String,
    password: String,
    emailError: String,
    snackbarHostState: SnackbarHostState,
    passwordError: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNavigateUp: () -> Unit,
    onCreateClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onAppleClick: () -> Unit,
) {
    UiScreen(
        appBar = {
            UiAppBar(
                onNavigateUp = onNavigateUp,
                title = stringResource(R.string.sign_up_title),
            )
        },
        navigationBar = {},
        snackbarHostState = snackbarHostState,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(id = R.string.sign_up_button_text),
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                EmailField(
                    value = email,
                    onValueChange = onEmailChange,
                    error = emailError,
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordField(
                    value = password,
                    onValueChange = onPasswordChange,
                    error = passwordError,
                )
                UiPrimaryButton(
                    onClick = onCreateClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    text = stringResource(id = R.string.sign_up_button_text)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(stringResource(id = R.string.sign_up_social_medias_buttons_title))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SocialMediaButton(image = ImageVector.vectorResource(id = R.drawable.icon_google),
                        onClick = { onGoogleClick() })
                    SocialMediaButton(
                        image = ImageVector.vectorResource(id = R.drawable.icon_apple),
                        onClick = { onAppleClick() })
                    SocialMediaButton(image = ImageVector.vectorResource(id = R.drawable.icon_facebook),
                        onClick = { onFacebookClick() })
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        })
}

@Composable
private fun EmailField(
    value: String,
    onValueChange: (String) -> Unit,
    error: String,
) {
    UiTextField(
        value = value,
        onValueChange = onValueChange,
        label = stringResource(R.string.sign_up_mail_title),
        hint = stringResource(R.string.sign_up_mail_hint),
        error = error,
    )
}

@Composable
private fun PasswordField(
    value: String, onValueChange: (String) -> Unit, error: String
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val visualTransformation =
        if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    val keyboardOptions =
        if (passwordVisible) KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        else KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)

    val trailingIcon = @Composable {
        IconButton(onClick = { passwordVisible = !passwordVisible }) {
            Icon(
                imageVector = if (passwordVisible) Icons.Filled.ArrowBack else Icons.Filled.ArrowForward,
                contentDescription = null
            )
        }
    }

    UiTextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,
        label = stringResource(R.string.sign_up_password_title),
        hint = stringResource(R.string.sign_up_password_hint),
        error = error,
    )
}

private fun onGoogleClick(
    googleSignInClient: GoogleSignInClient,
    googleSignInLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    val signInIntent = googleSignInClient.signInIntent
    googleSignInLauncher.launch(signInIntent)
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSignUpScreen() {
    UiTheme {
        SignUpScreen(
            navigateUp = {},
            navigateToMain = {},
            navigateToFacebook = {},
            navigateToApple = {},
        )
    }
}