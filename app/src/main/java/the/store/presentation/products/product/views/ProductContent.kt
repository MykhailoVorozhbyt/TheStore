package the.store.presentation.products.product.views

import android.Manifest
import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.base.views.BaseButton
import com.example.core.domain.entities.CurrencyList
import com.example.core.domain.entities.MeasurementsList
import com.example.core.domain.entities.ProductInputChipEntity
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultHorizontalPadding
import com.example.core.utils.extensions.modifiers.defaultIconSize
import com.example.core.utils.extensions.modifiers.defaultPadding
import com.example.core.utils.helpers.showMessage
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import the.store.presentation.products.product.models.ProductUiState
import the.store.utils.DecimalVisualTransformation
import the.store.utils.imageRequestBuilder

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ProductScreenPreview() {
    ProductContent(LocalContext.current, ProductUiState(), {}, {}, {}, {}, {}, {}, {}, {}, {})
}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProductContent(
    context: Context,
    state: ProductUiState,
    photoChanged: (Uri) -> Unit,
    deletePhotoUri: () -> Unit,
    nameChanged: (String) -> Unit,
    priceChanged: (String) -> Unit,
    measurementsChanged: (Long) -> Unit,
    currencyChanged: (Long) -> Unit,
    descriptionChanged: (String) -> Unit,
    barcodeChanged: (String) -> Unit,
    deleteProduct: (Long) -> Unit
) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)

    var pickPhotoEnable by remember {
        mutableStateOf(true)
    }

    val pickSinglePhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                photoChanged.invoke(uri)
            }
            pickPhotoEnable = true
        }
    )

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            pickPhotoEnable = true
            if (isGranted) {
                pickSinglePhoto.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            } else {
                showMessage(context, R.string.permission_was_denied)
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(TheStoreColors.blackOrWhiteColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .defaultPadding()
                .height(120.dp)
                .background(TheStoreColors.whiteOrBlackColor, baseRoundedCornerShape()),
            contentAlignment = Alignment.Center
        ) {
            val photoUri = state.photoUri
            if (photoUri == null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = rememberVectorPainter(Icons.Filled.ShoppingCart),
                        contentDescription = "worker icon",
                        tint = TheStoreColors.blackOrWhiteColor
                    )
                    Text(
                        text = stringResource(id = R.string.add_photo),
                        color = TheStoreColors.blackOrWhiteColor,
                    )
                }
            } else {
                Image(
                    painter = imageRequestBuilder(
                        context,
                        photoUri,
                        R.drawable.ic_person
                    ),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = TheStoreColors.whiteOrBlackColor,
                            shape = baseRoundedCornerShape()
                        )
                        .clip(baseRoundedCornerShape()),
                    contentDescription = null
                )
            }

            IconButton(
                onClick = {
                    when {
                        cameraPermissionState.status.isGranted -> {
                            pickSinglePhoto.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                            pickPhotoEnable = false
                        }

                        cameraPermissionState.status.shouldShowRationale -> {
                            requestPermissionLauncher.launch(cameraPermissionState.permission)
                            pickPhotoEnable = false
                        }

                        else -> {
                            showMessage(context, R.string.app_needs_access_to_photo)
                        }
                    }
                },
                enabled = pickPhotoEnable,
                modifier = Modifier
                    .align(Alignment.TopEnd),
            ) {
                Icon(
                    painter = rememberVectorPainter(Icons.Filled.AddCircle),
                    contentDescription = null,
                    tint = TheStoreColors.blackOrWhiteColor,
                    modifier = Modifier.defaultIconSize()
                )
            }
            if (photoUri != null) {
                IconButton(
                    onClick = {
                        deletePhotoUri.invoke()
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd),
                ) {
                    Icon(
                        painter = rememberVectorPainter(Icons.Filled.Delete),
                        contentDescription = null,
                        tint = TheStoreColors.blackOrWhiteColor,
                        modifier = Modifier.defaultIconSize()
                    )
                }
            }


        }

        InputTextField(
            onValueChange = { resultText ->
                nameChanged.invoke(resultText)
            },
            titleText = stringResource(id = R.string.name),
            hintText = stringResource(id = R.string.input_name),
            textValue = state.name,
            columnModifier = Modifier.defaultHorizontalPadding(),
            errorMessage = stringResource(id = state.inputDataErrorState.nameErrorState.errorStringRes),
            isError = state.inputDataErrorState.nameErrorState.hasError,
        )
        InputTextField(
            onValueChange = { resultText ->
                priceChanged.invoke(resultText)
            },
            titleText = stringResource(id = R.string.price),
            hintText = stringResource(id = R.string.input_price),
            textValue = state.price.toString(),
            visualTransformation = DecimalVisualTransformation(),
            columnModifier = Modifier.defaultHorizontalPadding(),
            errorMessage = stringResource(id = state.inputDataErrorState.priceErrorState.errorStringRes),
            isError = state.inputDataErrorState.priceErrorState.hasError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Text(
            text = stringResource(id = R.string.select_measurements),
            modifier = Modifier
                .fillMaxWidth()
                .defaultHorizontalPadding(),
            style = TextStyle(
                color = TheStoreColors.whiteOrBlackColor
            )
        )
        var selectedMeasurementItem by remember { mutableLongStateOf(MeasurementsList[0].id) }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .defaultHorizontalPadding()
        ) {
            items(MeasurementsList) { item ->
                MeasurementsInputChip(item, item.id == selectedMeasurementItem) {
                    selectedMeasurementItem = it
                    measurementsChanged.invoke(it)
                }
            }
        }

        Text(
            text = stringResource(id = R.string.select_currency),
            modifier = Modifier
                .fillMaxWidth()
                .defaultHorizontalPadding(),
            style = TextStyle(
                color = TheStoreColors.whiteOrBlackColor
            )
        )
        var selectedCurrencyItem by remember { mutableLongStateOf(CurrencyList[0].id) }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .defaultHorizontalPadding()
        ) {
            items(CurrencyList) { item ->
                MeasurementsInputChip(item, item.id == selectedCurrencyItem) {
                    selectedCurrencyItem = it
                    currencyChanged.invoke(it)
                }
            }
        }

        InputTextField(
            onValueChange = { resultText ->
                descriptionChanged.invoke(resultText)
            },
            titleText = stringResource(id = R.string.description),
            hintText = stringResource(id = R.string.input_description),
            textValue = state.name,
            columnModifier = Modifier.defaultHorizontalPadding()
        )
        InputTextField(
            onValueChange = { resultText ->
                barcodeChanged.invoke(resultText)
            },
            titleText = stringResource(id = R.string.barcode),
            hintText = stringResource(id = R.string.input_barcode),
            textValue = state.name,
            columnModifier = Modifier.defaultHorizontalPadding()
        )

        if (state.id != 0L) {
            BaseButton(
                text = stringResource(id = R.string.delete_product),
                buttonModifier = Modifier
                    .fillMaxWidth()
                    .defaultHorizontalPadding()
            ) {
                deleteProduct.invoke(state.id)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeasurementsInputChip(
    item: ProductInputChipEntity,
    selectedItem: Boolean,
    itemClick: (Long) -> Unit
) {
    InputChip(
        modifier = Modifier.padding(horizontal = 6.dp),
        selected = selectedItem,
        onClick = {
            itemClick.invoke(item.id)
        },
        label = {
            Text(stringResource(id = item.textId))
        },
        avatar = {
            if (selectedItem) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    modifier = Modifier.size(
                        size = InputChipDefaults.AvatarSize
                    )
                )
            } else {
                Icon(
                    painter = painterResource(item.iconId),
                    contentDescription = null,
                    modifier = Modifier.size(
                        size = InputChipDefaults.AvatarSize
                    )
                )
            }
        },
        colors = InputChipDefaults.inputChipColors(
            labelColor = TheStoreColors.whiteOrBlackColor,
            selectedLabelColor = TheStoreColors.whiteOrBlackColor,
            selectedLeadingIconColor = TheStoreColors.whiteOrBlackColor,
            selectedContainerColor = TheStoreColors.whiteOrBlackColor.copy(alpha = 0.2f),
        ),
        border = InputChipDefaults.inputChipBorder(
            selectedBorderColor = TheStoreColors.whiteOrBlackColor,
            selectedBorderWidth = 1.2.dp,
            borderColor = TheStoreColors.whiteOrBlackColor
        )
    )
}