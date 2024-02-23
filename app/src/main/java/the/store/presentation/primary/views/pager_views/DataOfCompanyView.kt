package the.store.presentation.primary.views.pager_views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.core.base.views.BaseCardView
import com.example.core.domain.entities.CompanyEntity
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultPadding
import com.example.core.utils.extensions.modifiers.smallVerticalPadding
import com.example.theme.BlackBoldTextStyle
import com.example.theme.BlackTextStyle
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor
import the.store.utils.convertToDate
import the.store.utils.imageRequestBuilder
import java.util.Calendar

@PreviewLightDark
@Composable
fun DataOfCompanyViewPreview() {
    Column {
        DataOfCompanyView(
            CompanyEntity(
                photoUri = "highsnobiety.com/static-assets/dato/1696613224-drake-for-all-the-dogs-lyrics-0.jpg?fp-x=0.5&fp-y=0.5&fit=crop&auto=compress%2Cformat&cs=srgb&ar=1440%3A960&w=1440",
                name = "Drake",
                description = "Drake description",
                createdAt = Calendar.getInstance().timeInMillis,
            )
        )
        DataOfCompanyView(
            CompanyEntity(
                photoUri = null,
                name = "Drake",
                description = "Drake description",
                createdAt = Calendar.getInstance().timeInMillis,
            )
        )
    }
}

@Composable
fun DataOfCompanyView(data: CompanyEntity?) {
    BaseCardView {
        if (data == null) {
            Column {
                Text(
                    text = stringResource(R.string.you_need_to_set_up_a_company),
                    style = BlackBoldTextStyle,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = stringResource(R.string.go_to_more_company),
                    style = BlackBoldTextStyle,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } else {
            Row {
                val photoUri = data.photoUri
                if (photoUri == null) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .defaultPadding()
                            .background(
                                TheStoreColors.blackOrWhiteColor,
                                baseRoundedCornerShape()
                            )
                            .defaultPadding(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Filled.Home),
                            contentDescription = "worker icon",
                            tint = TheStoreColors.whiteOrBlackColor
                        )
                        Text(
                            text = stringResource(id = R.string.add_photo),
                            style = BlackBoldTextStyle
                        )
                    }
                } else {
                    Image(
                        painter = imageRequestBuilder(
                            LocalContext.current,
                            photoUri,
                            R.drawable.ic_warehouse
                        ),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .defaultPadding()
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
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = stringResource(id = R.string.company),
                        style = BlackTextStyle,
                        modifier = Modifier.smallVerticalPadding()
                    )
                    Text(
                        text = stringResource(id = R.string.name_and_value, data.name),
                        style = BlackTextStyle
                    )
                    if (data.description.isNotBlank()) {
                        Text(
                            text = stringResource(
                                id = R.string.description_with_value,
                                data.description
                            ),
                            style = BlackTextStyle
                        )
                    }
                    Text(
                        text = data.createdAt.convertToDate(),
                        style = BlackTextStyle
                    )
                }

            }
        }
    }
}