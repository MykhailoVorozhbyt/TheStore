package the.store.presentation.primary.views.bottom_sheet_views


import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.base.views.BaseButton
import com.example.core.utils.extensions.modifiers.defaultHorizontalPadding
import com.example.core.utils.extensions.modifiers.smallHorizontalPadding
import com.example.core.utils.extensions.modifiers.smallVerticalPadding
import com.example.theme.BlackTextStyle
import com.example.theme.R
import kotlinx.coroutines.launch
import the.store.presentation.primary.PrimaryViewModel
import the.store.presentation.primary.views.pager_views.PrimaryViewPagerContent

@Composable
@Preview
fun PrimaryBottomSheetPreview(viewModel: PrimaryViewModel = hiltViewModel()) {
    PrimaryBottomSheetView(viewModel) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            PrimaryViewPagerContent()
            BaseButton(
                text = stringResource(id = R.string.shift_action),
                buttonModifier = Modifier
                    .fillMaxWidth()
                    .smallVerticalPadding()
                    .defaultHorizontalPadding(),
                revertColor = false
            ) {
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun PrimaryBottomSheetView(
    viewModel: PrimaryViewModel,
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        confirmValueChange = { it != SheetValue.Hidden },
        skipHiddenState = false,
    )

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState)

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(20.dp),
        sheetTonalElevation = 20.dp,
        sheetPeekHeight = 50.dp,
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            BottomSheetContent {
                Column {
                    BottomSheetSearchView(viewModel)
                    CountriesListView()
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.hasExpandedState) {
                            bottomSheetScaffoldState.bottomSheetState.partialExpand()
                        }
                    }
                })
            },
    ) {
        content()
    }
}

@Composable
@Preview
fun CountryListPreview(viewModel: PrimaryViewModel = hiltViewModel()) {
    BottomSheetContent {}
}

@Composable
fun CountriesListView() {
    LazyColumn {
        items(countriesList) { (country, flag) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultHorizontalPadding()
                    .smallHorizontalPadding()
            ) {
                Text(
                    text = flag,
                    modifier = Modifier.padding(end = 20.dp),
                    style = BlackTextStyle
                )
                Text(text = country, style = BlackTextStyle)
            }
        }
    }
}

val countriesList = listOf(
    Pair("United States", "\uD83C\uDDFA\uD83C\uDDF8"),
    Pair("Canada", "\uD83C\uDDE8\uD83C\uDDE6"),
    Pair("India", "\uD83C\uDDEE\uD83C\uDDF3"),
    Pair("Germany", "\uD83C\uDDE9\uD83C\uDDEA"),
    Pair("France", "\uD83C\uDDEB\uD83C\uDDF7"),
    Pair("Japan", "\uD83C\uDDEF\uD83C\uDDF5"),
    Pair("China", "\uD83C\uDDE8\uD83C\uDDF3"),
    Pair("Brazil", "\uD83C\uDDE7\uD83C\uDDF7"),
    Pair("Australia", "\uD83C\uDDE6\uD83C\uDDFA"),
    Pair("United Kingdom", "\uD83C\uDDEC\uD83C\uDDE7"),
    Pair("United 2 States", "\uD83C\uDDFA\uD83C\uDDF8"),
    Pair("Canada 2", "\uD83C\uDDE8\uD83C\uDDE6"),
    Pair("India 2", "\uD83C\uDDEE\uD83C\uDDF3"),
    Pair("Germany 2", "\uD83C\uDDE9\uD83C\uDDEA"),
    Pair("France 2", "\uD83C\uDDEB\uD83C\uDDF7"),
    Pair("Japan 2", "\uD83C\uDDEF\uD83C\uDDF5"),
    Pair("China 2", "\uD83C\uDDE8\uD83C\uDDF3"),
    Pair("Brazil 2", "\uD83C\uDDE7\uD83C\uDDF7"),
    Pair("Australia 2", "\uD83C\uDDE6\uD83C\uDDFA"),
    Pair("United 2 Kingdom", "\uD83C\uDDEC\uD83C\uDDE7")
)

