package the.store.presentation.workers.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.models.db_entity.WorkerEntity
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.utils.extensions.modifiers.defaultTextStartPadding
import com.example.core.utils.extensions.modifiers.smallHorizontalPadding
import com.example.core.utils.extensions.modifiers.smallPadding
import com.example.core.utils.extensions.modifiers.smallVerticalPadding
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor
import the.store.presentation.workers.models.WorkersUiState
import the.store.presentation.workers.models.workersList


@Preview
@Composable
fun WorkersScreenBodyPreview() {
    WorkersScreenContent(WorkersUiState(workersList = workersList), {}, {})
}

@Composable
fun WorkersScreenContent(
    uiState: WorkersUiState,
    searchText: (String) -> Unit,
    workerClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TheStoreColors.blackOrWhiteColor)
            .smallVerticalPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTextField(
            onValueChange = { resultText ->
                searchText.invoke(resultText)
            },
            hintText = stringResource(id = R.string.input_worker_name),
            textValue = uiState.searchedName,
            columnModifier = Modifier.smallHorizontalPadding()
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            itemsIndexed(uiState.workersList) { index, item ->
                WorkerItem(
                    item,
                    index == 0,
                    index == uiState.workersList.size - 1
                ) { id ->
                    workerClick.invoke(id)
                }
            }
        }

    }
}

@Composable
fun WorkersScreenBody(
    addWorker: () -> Unit,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.workers),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(),
                        color = TheStoreColors.whiteOrBlackColor,
                    )
                },
                backgroundColor = TheStoreColors.blackOrWhiteColor,
                modifier = Modifier.fillMaxWidth(),
                actions = {
                    Icon(
                        rememberVectorPainter(Icons.Filled.Add),
                        contentDescription = null,
                        tint = TheStoreColors.whiteOrBlackColor,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                addWorker.invoke()
                            }

                    )
                }
            )
        },
        content = { pageContent.invoke(it) }
    )
}

@Preview
@Composable
fun WorkerItemPreview() {
    WorkerItem(
        WorkerEntity(
            id = 0,
            name = "Misha",
            surname = "Vorozhbyt",
            password = "",
            phone = "",
            emailAddress = "",
            vatIdentificationNumber = "",
        ),
        true,
        false
    ) {}
}

@Composable
fun WorkerItem(
    worker: WorkerEntity,
    isFirsItem: Boolean,
    isLastITem: Boolean,
    click: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 1.dp)
            .clip(itemRoundedCorner(isFirsItem, isLastITem))
            .background(TheStoreColors.whiteOrBlackColor)
            .clickable { click.invoke(worker.id) }
            .smallPadding(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            rememberVectorPainter(Icons.Filled.Person),
            contentDescription = null,
            tint = TheStoreColors.blackOrWhiteColor,
        )
        Text(
            text = worker.name,
            modifier = Modifier.defaultTextStartPadding(),
        )
        Text(
            text = worker.surname,
            modifier = Modifier
                .weight(1f)
                .defaultTextStartPadding(),
        )
        Icon(
            rememberVectorPainter(Icons.Filled.KeyboardArrowRight),
            contentDescription = null,
            tint = TheStoreColors.blackOrWhiteColor,
            modifier = Modifier
        )
    }
}


private fun itemRoundedCorner(isFirsItem: Boolean, isLastITem: Boolean) = when {
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