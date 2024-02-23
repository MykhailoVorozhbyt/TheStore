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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.core.domain.constants.Constants
import com.example.theme.TheStoreColors
import com.example.theme.whiteOrBlackColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import the.store.presentation.primary.models.PrimaryUiState

@PreviewLightDark
@Composable
fun PrimaryViewPagerContentPreview() {
    PrimaryViewPagerContent(PrimaryUiState())
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PrimaryViewPagerContent(
    state: PrimaryUiState
) {
    val coroutineScope = rememberCoroutineScope()
    val indicatorColor = TheStoreColors.whiteOrBlackColor
    val items by remember {
        mutableStateOf(
            listOf(
                Constants.DATA_OF_COMPANY,
                Constants.DATA_OF_WORKER,
            )
        )
    }
    val pagerState = rememberPagerState(pageCount = {
        items.size
    })
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
        ) { page ->
            when (items[page]) {
                Constants.DATA_OF_COMPANY -> {
                    DataOfCompanyView(state.companyInfo)
                }

                Constants.DATA_OF_WORKER -> {
                    WorkerDataView(state.workerInfo)
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
                    if (pagerState.currentPage == iteration) {
                        indicatorColor
                    } else {
                        indicatorColor.copy(alpha = 0.4f)
                    }
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
    with(pagerState) {
        LaunchedEffect(currentPage) {
            // convert compose state into flow
            snapshotFlow { currentPage }.collectLatest {
                delay(Constants.HORIZONTAL_PAGER_AUTO_SCROLL_TIME)
                val nextPage = if (currentPage < pageCount - 1) currentPage + 1 else 0
                coroutineScope.launch {
                    animateScrollToPage(nextPage)
                }
            }
        }
    }
}