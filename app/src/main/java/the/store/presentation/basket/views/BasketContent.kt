package the.store.presentation.basket.views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import com.example.core.domain.entities.CurrencyList
import com.example.core.domain.entities.MeasurementsList
import com.example.core.domain.entities.ProductEntity
import com.example.core.domain.entities.getCurrencyById
import com.example.core.domain.entities.getMeasurementById
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.ui.widget.EmptyListView
import com.example.core.ui.widget.ProgressIndicator
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.basketListIconSize
import com.example.core.utils.extensions.modifiers.defaultHorizontalPadding
import com.example.core.utils.extensions.modifiers.defaultTextStartPadding
import com.example.core.utils.extensions.modifiers.defaultTopPadding
import com.example.core.utils.extensions.modifiers.smallHorizontalPadding
import com.example.core.utils.extensions.modifiers.smallPadding
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.WhiteBoldTextStyle
import com.example.theme.WhiteTextStyle
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor
import the.store.presentation.basket.models.BasketUiState
import the.store.utils.TOperation
import the.store.utils.imageRequestBuilder
import the.store.utils.workerItemRoundedCorner

@PreviewLightDark
@Composable
fun BasketContentPreview() {
    BasketContent(BasketUiState(), {}, {})
}


@Composable
fun BasketContent(
    state: BasketUiState,
    searchText: TOperation<String>,
    productClick: TOperation<ProductEntity>
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        InputTextField(
            onValueChange = { resultText ->
                searchText.invoke(resultText)
            },
            textValue = state.searchedProduct,
            columnModifier = Modifier
                .defaultHorizontalPadding()
                .defaultTopPadding()
        )
        if (state.productList.isEmpty()) {
            EmptyListView()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .smallHorizontalPadding()
            ) {
                itemsIndexed(state.productList) { index, item ->
                    ProductContentItem(
                        context,
                        item,
                        index == 0,
                        index == state.productList.size - 1
                    ) {
                        productClick.invoke(it)
                    }
                }
            }
        }

    }
}

@PreviewLightDark
@Composable
fun ProductContentItemPreviewLightDark() {
    ProductContentItem(
        LocalContext.current,
        ProductEntity(
            id = 0,
            photoUri = "222",
            name = "Misha",
            1500.0,
            R.string.kilogram,
            R.string.usd
        ),
        true,
        false
    ) {}
}

@Composable
fun ProductContentItem(
    context: Context,
    product: ProductEntity,
    isFirsItem: Boolean,
    isLastITem: Boolean,
    productClick: TOperation<ProductEntity>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 1.dp)
            .clip(workerItemRoundedCorner(isFirsItem, isLastITem))
            .background(TheStoreColors.blackOrWhiteColor)
//            .clickable { productClick(product.id) }
            .smallPadding(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val photoUri = product.photoUri
        if (photoUri.isNullOrBlank()) {
            Icon(
                rememberVectorPainter(Icons.Filled.Person),
                contentDescription = null,
                tint = TheStoreColors.whiteOrBlackColor,
                modifier = Modifier.basketListIconSize()
            )
        } else {
            val painter = imageRequestBuilder(
                context,
                photoUri,
                R.drawable.ic_hourglass_empty
            )
            if (painter.state is AsyncImagePainter.State.Loading) {
                ProgressIndicator(
                    color = TheStoreColors.whiteOrBlackColor,
                    modifier = Modifier.basketListIconSize()
                )
            } else {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = TheStoreColors.whiteOrBlackColor,
                            shape = baseRoundedCornerShape()
                        )
                        .basketListIconSize()
                        .clip(baseRoundedCornerShape())
                )
            }

        }
        Column(
            modifier = Modifier
                .defaultTextStartPadding()
                .weight(1f)
        ) {
            Text(
                text = product.name,
                minLines = 1,
                style = WhiteBoldTextStyle
            )
            Text(
                text = stringResource(R.string.price_with_value, product.price.toString()),
                minLines = 1,
                style = WhiteTextStyle
            )
            val measurement = getMeasurementById(product.measurementId.toLong())
            val currency = getCurrencyById(product.currencyId.toLong())
            Text(
                text = stringResource(id = measurement.textId) + " | " + stringResource(id = currency.textId),
                minLines = 1,
                style = WhiteTextStyle
            )
        }
        IconButton(onClick = {
            productClick.invoke(product)
        }) {
            Icon(
                rememberVectorPainter(Icons.Filled.Add),
                contentDescription = null,
                tint = TheStoreColors.whiteOrBlackColor,
            )
        }
    }
}