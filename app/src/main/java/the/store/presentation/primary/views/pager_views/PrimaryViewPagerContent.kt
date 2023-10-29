package the.store.presentation.primary.views.pager_views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.constants.Constants
import com.example.theme.Black

@Preview
@Composable
fun PrimaryViewPagerContentPreview() {
    PrimaryViewPagerContent()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PrimaryViewPagerContent() {
    val items by remember {
        mutableStateOf(listOf(Constants.DATA_OF_THE_CURRENT_SHIFT, Constants.SIMPLE_MODE))
    }
    val pagerState = rememberPagerState(initialPage = 1)

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalPager(
            pageCount = items.size,
            state = pagerState
        ) { page ->
            when (items[page]) {
                Constants.DATA_OF_THE_CURRENT_SHIFT -> {
                    DataOfTheCurrentShiftView()
                }

                Constants.SIMPLE_MODE -> {
                    SimpleModeView()
                }
            }
        }
        Row(
            Modifier
                .height(25.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(items.size) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Black else Black.copy(alpha = 0.4f)
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }


}