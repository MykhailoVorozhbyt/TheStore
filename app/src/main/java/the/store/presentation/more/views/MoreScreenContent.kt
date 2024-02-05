package the.store.presentation.more.views

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.domain.more_screen_dto.BaseMoreScreenItemDto
import com.example.core.domain.more_screen_dto.MoreScreenButtonDto
import com.example.core.domain.more_screen_dto.MoreScreenTitleDto
import com.example.core.utils.builder.MoreScreenBuilder
import com.example.core.utils.enums.MoreScreenClickAction
import com.example.core.utils.extensions.modifiers.defaultHorizontalPadding
import com.example.core.utils.extensions.modifiers.defaultTextStartPadding
import com.example.core.utils.extensions.modifiers.smallVerticalPadding
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun MoreScreenContentPreview() {

    MoreScreenContent(MoreScreenBuilder(LocalContext.current).build()) {}
}

@Composable
fun MoreScreenContent(
    uiListItems: List<BaseMoreScreenItemDto>,
    clickAction: (MoreScreenClickAction) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(TheStoreColors.blackOrWhiteColor)
    ) {
        items(uiListItems) { item ->
            when (item) {
                is MoreScreenButtonDto -> {
                    MoreScreenButtonItem(item) { click ->
                        clickAction.invoke(click)
                    }
                }

                is MoreScreenTitleDto -> {
                    MoreScreenTitleItem(item)
                }
            }
        }
    }
}


@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun MoreScreenButtonItemPreview() {
    val context = LocalContext.current
    MoreScreenButtonItem(
        MoreScreenButtonDto(
            icon = R.drawable.ic_warehouse,
            clickAction = MoreScreenClickAction.CompanyClick,
            text = context.getString(R.string.company),
            textStyle = null,
        )
    ) {}
}

@Composable
private fun MoreScreenButtonItem(
    model: MoreScreenButtonDto, clickAction: (MoreScreenClickAction) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            clickAction.invoke(model.clickAction)
        }
        .background(TheStoreColors.blackOrWhiteColor)
        .smallVerticalPadding()
        .defaultHorizontalPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = model.icon),
            tint = TheStoreColors.whiteOrBlackColor,
            contentDescription = model::class.java.name,
        )

        Text(
            text = model.text,
            modifier = Modifier
                .weight(1f)
                .defaultTextStartPadding(),
            color = TheStoreColors.whiteOrBlackColor
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_navigate_next),
            contentDescription = null,
            tint = TheStoreColors.whiteOrBlackColor
        )
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun MoreScreenTitleItemPreview() {
    val context = LocalContext.current
    MoreScreenTitleItem(
        MoreScreenTitleDto(
            text = context.getString(R.string.additional_function),
            textStyle = R.style.PrimaryTextStyle16Bold,
        )
    )
}

@Composable
private fun MoreScreenTitleItem(model: MoreScreenTitleDto) {
    Text(
        text = model.text,
        modifier = Modifier
            .fillMaxWidth()
            .smallVerticalPadding()
            .defaultHorizontalPadding(),
        color = TheStoreColors.whiteOrBlackColor
    )
}