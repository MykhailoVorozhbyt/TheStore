package the.store.presentation.primary.views.pager_views

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.core.base.views.BaseCardView
import com.example.core.domain.entities.WorkerEntity
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultPadding
import com.example.core.utils.extensions.modifiers.defaultStartPadding
import com.example.core.utils.extensions.modifiers.defaultVerticalPadding
import com.example.core.utils.extensions.modifiers.smallVerticalPadding
import com.example.theme.BlackBoldTextStyle
import com.example.theme.BlackTextStyle
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import the.store.utils.imageRequestBuilder

@PreviewLightDark
@Composable
fun DataOfTheCurrentShiftViewPreview() {
    Column {
        WorkerDataView(
            WorkerEntity(
                id = 1,
                photo = Uri.parse("222"),
                name = "Misha",
                surname = "Vorozhbyt",
                phone = "+38011111111",
            )
        )
        WorkerDataView(
            WorkerEntity(
                id = 1,
                photo = null,
                name = "Misha",
                surname = "Vorozhbyt",
                phone = "+38011111111",
            )
        )
    }
}

@Composable
fun WorkerDataView(data: WorkerEntity?) {
    BaseCardView {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            val photoUri = data?.photo
            if (photoUri != null) {
                Image(
                    painter = imageRequestBuilder(
                        LocalContext.current,
                        photoUri,
                        R.drawable.ic_person
                    ),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .defaultStartPadding()
                        .defaultVerticalPadding()
                        .border(
                            width = 2.dp,
                            color = TheStoreColors.blackOrWhiteColor,
                            shape = baseRoundedCornerShape()
                        )
                        .clip(baseRoundedCornerShape())
                        .weight(1f),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .defaultPadding(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.worker_data),
                    style = BlackBoldTextStyle,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.smallVerticalPadding()
                )
                Text(
                    text = stringResource(
                        id = R.string.name_and_value,
                        data?.name.orEmpty()
                    ),
                    style = BlackTextStyle,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = stringResource(
                        id = R.string.surname_and_value,
                        data?.surname.orEmpty()
                    ),
                    style = BlackTextStyle,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = stringResource(
                        id = R.string.phone_and_value,
                        data?.phone.orEmpty()
                    ),
                    style = BlackTextStyle,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}