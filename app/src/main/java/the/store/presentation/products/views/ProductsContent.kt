package the.store.presentation.products.views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import com.example.core.domain.entities.ProductEntity
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.ui.widget.EmptyListView
import com.example.core.ui.widget.ProgressIndicator
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultListIconSize
import com.example.core.utils.extensions.modifiers.smallHorizontalPadding
import com.example.core.utils.extensions.modifiers.smallPadding
import com.example.core.utils.extensions.modifiers.smallVerticalPadding
import com.example.theme.TheStoreColors
import com.example.theme.whiteOrBlackColor
import com.example.theme.blackOrWhiteColor
import the.store.presentation.products.models.ProductsUiState
import the.store.presentation.products.models.ProductEntityList
import the.store.utils.imageRequestBuilder

@PreviewLightDark
@Composable
fun WorkersScreenBodyPreview() {
    ProductsContent(
        dataState = ProductsUiState(productList = ProductEntityList()),
        searchText = {},
        productClick = {},
    )
}

@Composable
fun ProductsContent(
    dataState: ProductsUiState,
    searchText: (String) -> Unit,
    productClick: (Long) -> Unit,
) {
    val context: Context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TheStoreColors.whiteOrBlackColor)
            .smallVerticalPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTextField(
            onValueChange = { resultText ->
                searchText.invoke(resultText)
            },
            hintText = stringResource(id = com.example.theme.R.string.input_name),
            textValue = dataState.searchedName,
            columnModifier = Modifier.smallHorizontalPadding()
        )
        if (dataState.productList.isEmpty()) {
            EmptyListView()
        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(count = 3),
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(space = 4.dp),
                verticalItemSpacing = 4.dp
            ) {
                itemsIndexed(dataState.productList) { index, item ->
                    ProductItem(
                        context,
                        item
                    ) {
                        productClick.invoke(it)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    context: Context,
    product: ProductEntity,
    productClick: (Long) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(baseRoundedCornerShape())
            .background(TheStoreColors.blackOrWhiteColor)
            .clickable {
                productClick.invoke(product.id)
            }
            .smallPadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val photoUri = product.photoUri
            if (!photoUri.isNullOrBlank()) {
                val painter = imageRequestBuilder(
                    context,
                    photoUri,
                    com.example.theme.R.drawable.ic_hourglass_empty
                )
                if (painter.state is AsyncImagePainter.State.Loading) {
                    ProgressIndicator(
                        color = TheStoreColors.whiteOrBlackColor,
                        modifier = Modifier.defaultListIconSize()
                    )
                } else {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = TheStoreColors.whiteOrBlackColor,
                                shape = baseRoundedCornerShape()
                            )
                            .defaultListIconSize()
                            .clip(baseRoundedCornerShape())
                    )
                }
            }
            val topPadding = if (photoUri.isNullOrBlank()) 0.dp else 6.dp
            Text(
                text = product.name,
                color = TheStoreColors.whiteOrBlackColor,
                modifier = Modifier.padding(top = topPadding)
            )

            IconButton(
                onClick = {
                    productClick.invoke(product.id)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .background(TheStoreColors.whiteOrBlackColor, CircleShape)
                    .size(25.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = com.example.theme.R.string.more),
                        color = TheStoreColors.blackOrWhiteColor
                    )
                    Icon(
                        painter = painterResource(com.example.theme.R.drawable.ic_navigate_next),
                        contentDescription = null,
                        tint = TheStoreColors.blackOrWhiteColor
                    )
                }
            }
        }
    }
}