package com.tripfolk.ui_kit.util

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
/**
 * Create an image painter that loads the given [url] from the network.
 *
 * @param url The URL to load the image from.
 */
@Composable
fun rememberNetworkPainter(
    url: String,
): Painter = rememberAsyncImagePainter(
    model = url,
)

/**
 * Create an image painter that loads the given [url] from the network.
 *
 * @param url The URL to load the image from.
 * @param placeholder The placeholder to display while loading the image.
 * @param error The error to display if the image fails to load.
 */
@Composable
fun rememberNetworkPainter(
    url: String,
    @DrawableRes placeholder: Int,
    @DrawableRes error: Int = placeholder,
): Painter = rememberAsyncImagePainter(
    model = url,
    placeholder = painterResource(placeholder),
    error = painterResource(error)

)

/**
 * Create an image painter that loads the given [url] from the network.
 *
 * @param url The URL to load the image from.
 * @param placeholder The placeholder to display while loading the image.
 * @param error The error to display if the image fails to load.
 */
@Composable
fun rememberNetworkPainter(
    url: String,
    placeholder: ImageVector,
    error: ImageVector = placeholder,
): Painter = rememberAsyncImagePainter(
    model = url,
    placeholder = rememberVectorPainter(placeholder),
    error = rememberVectorPainter(error)
)