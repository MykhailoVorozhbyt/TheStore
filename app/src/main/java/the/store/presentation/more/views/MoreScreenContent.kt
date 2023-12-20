package the.store.presentation.more.views

import android.content.res.Configuration
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.domain.more_screen_dto.BaseMoreScreenItemDto
import com.example.core.domain.more_screen_dto.MoreScreenButtonDto
import com.example.core.domain.more_screen_dto.MoreScreenTitleDto
import com.example.core.utils.enums.MoreScreenClickAction
import com.example.core.utils.extensions.modifiers.defaultHorizontalPadding
import com.example.core.utils.extensions.modifiers.defaultTextStartPadding
import com.example.core.utils.extensions.modifiers.smallVerticalPadding
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor

@Composable
fun MoreScreenContent(
    uiListItems: List<BaseMoreScreenItemDto>,
    clickAction: (MoreScreenClickAction) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
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
            icon = R.drawable.ic_person,
            iconColor = TheStoreColors.whiteOrBlackColor,
            clickAction = MoreScreenClickAction.UserProfileClick,
            text = context.getString(R.string.profile),
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
        .smallVerticalPadding()
        .background(TheStoreColors.blackOrWhiteColor)
        .defaultHorizontalPadding()
    ) {
        Icon(
            painter = painterResource(id = model.icon),
            tint = model.iconColor,
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
            contentDescription = "goTo",
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
            .defaultHorizontalPadding()
    )
}