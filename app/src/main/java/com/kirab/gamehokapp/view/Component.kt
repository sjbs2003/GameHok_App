package com.kirab.gamehokapp.view

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun GameHokImage(
    @DrawableRes imageRes: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageRes)
            .crossfade(true)
            .size(Size.ORIGINAL)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}