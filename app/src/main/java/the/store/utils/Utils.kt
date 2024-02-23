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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun workerItemRoundedCorner(isFirsItem: Boolean, isLastItem: Boolean) = when {
    isFirsItem && isLastItem -> {
        RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp)
    }

    isFirsItem -> {
        RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp)
    }

    isLastItem -> {
        RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp)
    }

    else -> {
        RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp)
    }
}

fun navigationBarItemRoundedCorner(isFirsItem: Boolean, isLastItem: Boolean) = when {
    isFirsItem -> {
        RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp)
    }

    isLastItem -> {
        RoundedCornerShape(0.dp, 10.dp, 10.dp, 0.dp)
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


@OptIn(ExperimentalPermissionsApi::class)
inline fun checkPermissionState(
    state: PermissionState,
    isGranted: () -> Unit,
    showRationale: () -> Unit,
    showSettings: () -> Unit,
) {
    when {
        state.status.isGranted -> {
            isGranted.invoke()
        }

        state.status.isGranted.not() || state.status.shouldShowRationale.not() -> {
            showRationale.invoke()
        }

        else -> {
            showSettings.invoke()
        }
    }
}

fun Long.convertToDate(format: String = DateConvertPatterns.dayMonthYearHourMinuteSecond): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(Date(this))
}
