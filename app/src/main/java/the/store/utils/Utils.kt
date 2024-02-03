package the.store.utils

import android.content.Context
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Size.Companion.ORIGINAL


fun itemRoundedCorner(isFirsItem: Boolean, isLastITem: Boolean) = when {
    isFirsItem && isLastITem -> {
        RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp)
    }

    isFirsItem -> {
        RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp)
    }

    isLastITem -> {
        RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp)
    }

    else -> {
        RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp)
    }
}

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

fun Double.format(digits: Int = 3) = "%.${digits}f".format(this).toDouble()

fun String?.toUriOrNull(): Uri? = if (this != null) Uri.parse(this) else null


