package the.store.presentation.workers.views

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import com.example.core.domain.db_entity.WorkerDbEntity
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.ui.widget.EmptyListView
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultListIconSize
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
import the.store.utils.imageRequestBuilder
import the.store.utils.itemRoundedCorner


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
fun WorkersScreenBodyPreview() {
    AddTopAppBar(stringResource(R.string.workers), {}) {
        WorkersScreenContent(WorkersUiState(workersList = workersList), {}, {})
    }
}

@Composable
fun WorkersScreenContent(
    uiState: WorkersUiState,
    searchText: (String) -> Unit,
    workerClick: (Long) -> Unit,
) {
    val context: Context = LocalContext.current
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
            hintText = stringResource(id = R.string.input_name),
            textValue = uiState.searchedName,
            columnModifier = Modifier.smallHorizontalPadding()
        )
        if (uiState.workersList.isEmpty()) {
            EmptyListView()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                itemsIndexed(uiState.workersList) { index, item ->
                    WorkerItem(
                        context,
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTopAppBar(
    title: String,
    addClick: () -> Unit,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        title,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(),
                        color = TheStoreColors.blackOrWhiteColor,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = TheStoreColors.whiteOrBlackColor
                ),
                modifier = Modifier.fillMaxWidth(),
                actions = {
                    Icon(
                        rememberVectorPainter(Icons.Filled.Add),
                        contentDescription = null,
                        tint = TheStoreColors.blackOrWhiteColor,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                addClick.invoke()
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
        LocalContext.current,
        WorkerDbEntity(
            id = 0,
            name = "Misha",
            surname = "Vorozhbyt",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        true,
        false
    ) {}
}

@Composable
fun WorkerItem(
    context: Context,
    worker: WorkerDbEntity,
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
        val photoUri = worker.photoUri
        if (photoUri.isNullOrBlank()) {
            Icon(
                rememberVectorPainter(Icons.Filled.Person),
                contentDescription = null,
                tint = TheStoreColors.blackOrWhiteColor,
                modifier = Modifier.defaultListIconSize()
            )
        } else {
            val painter = imageRequestBuilder(
                context,
                photoUri,
                R.drawable.ic_person
            )
            if (painter.state is AsyncImagePainter.State.Loading) {
                CircularProgressIndicator(
                    color = TheStoreColors.blackOrWhiteColor,
                    modifier = Modifier.defaultListIconSize()
                )
            } else {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = TheStoreColors.blackOrWhiteColor,
                            shape = baseRoundedCornerShape()
                        )
                        .defaultListIconSize()
                        .clip(baseRoundedCornerShape())
                )
            }

        }
        Text(
            text = worker.name,
            modifier = Modifier.defaultTextStartPadding(),
            style = TextStyle(
                color = TheStoreColors.blackOrWhiteColor
            )
        )
        Text(
            text = worker.surname,
            modifier = Modifier
                .weight(1f)
                .defaultTextStartPadding(),
            style = TextStyle(
                color = TheStoreColors.blackOrWhiteColor
            )
        )
        Icon(
            rememberVectorPainter(Icons.Filled.KeyboardArrowRight),
            contentDescription = null,
            tint = TheStoreColors.blackOrWhiteColor,
            modifier = Modifier
        )
    }
}