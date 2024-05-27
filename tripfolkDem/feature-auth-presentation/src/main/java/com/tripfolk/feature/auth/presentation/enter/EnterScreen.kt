package com.tripfolk.feature.auth.presentation.enter

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tripfolk.feature.auth.presentation.R
import com.tripfolk.ui_kit.component.appbar.UiAppBar
import com.tripfolk.ui_kit.component.button.block.UiButtonBlock
import com.tripfolk.ui_kit.component.button.primary.UiPrimaryButton
import com.tripfolk.ui_kit.component.screen.UiScreen
import com.tripfolk.ui_kit.theme.UiIcons
import com.tripfolk.ui_kit.theme.UiTheme
import com.tripfolk.ui_kit.util.rememberNetworkPainter

@Composable
fun EnterScreen(
    viewModel: EnterViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit,
    navigateToMain: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.isSignedUp.let { LaunchedEffect(it) { if (it) navigateToMain() } }
    viewModel.signUpError?.let { LaunchedEffect(it) { snackbarHostState.showSnackbar(it.message ?: it.toString()) } }

    Layout(
        snackbarHostState = snackbarHostState,
        onSignInAsGuestClick = { viewModel.signInAsGuest() },
        onSignInClick = navigateToSignIn,
        onSignUpClick = navigateToSignUp,
    )
}

@Composable
private fun Layout(
    snackbarHostState: SnackbarHostState,
    onSignInAsGuestClick: () -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    UiScreen(
        appBar = {
            UiAppBar(
                title = stringResource(R.string.enter_screen_title),
            )
        },
        buttons = {
                UiButtonBlock {
                    SingInButton(
                        onClick = onSignInClick,
                    )
                    SingUpButton(
                        onClick = onSignUpClick,
                    )
                    GuestButton(
                        onClick = onSignInAsGuestClick,
                    )
                }
        }) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Hey, you got rick rolled"
            )
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .size(350.dp)
                    .clip(CircleShape),
                painter = rememberNetworkPainter(
                    "https://static.wikia.nocookie.net/memes9731/images/c/c3/RickRoll.jpg/revision/latest?cb=20170921170528&path-prefix=ru",
                    UiIcons.Person
                ),
                contentDescription = null,
            )
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
    }
}

@Composable
private fun SingInButton(
    onClick: () -> Unit,
) {
    UiPrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.enter_sign_in_button),
        onClick = onClick,
    )
}

@Composable
private fun SingUpButton(
    onClick: () -> Unit,
) {
    UiPrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.enter_sign_up_button),
        onClick = onClick,
    )
}


@Composable
private fun GuestButton(
    onClick: () -> Unit,
) {
    UiPrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.enter_guest_button),
        onClick = onClick,
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        Layout(
            snackbarHostState = SnackbarHostState(),
            onSignInAsGuestClick = {},
            onSignInClick = {},
            onSignUpClick = {},
        )
    }
}