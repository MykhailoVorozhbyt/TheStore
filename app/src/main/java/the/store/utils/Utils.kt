package the.store.utils

import android.content.Context
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Size.Companion.ORIGINAL


@Composable
fun imageRequestBuilder(context: Context, uri: String, @DrawableRes placeholderResId: Int) =
    rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(uri)
            .diskCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .size(ORIGINAL)
            .placeholder(placeholderResId)
            .build()
    )

@Composable
fun imageRequestBuilder(context: Context, uri: Uri, @DrawableRes placeholderResId: Int) =
    rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(uri)
            .diskCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .size(ORIGINAL)
            .placeholder(placeholderResId)
            .build()
    )