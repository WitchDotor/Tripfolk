package com.tripfolk.ui_kit.component.button.social_media

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SocialMediaButton(
    image: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Image(
        imageVector = image,
        contentDescription = null,
        modifier = modifier.clickable { onClick() }
    )
}

@Preview
@Composable
private fun Preview() {
    SocialMediaButton(
        image = Icons.Default.ArrowForward,
        onClick = {},
    )
}
