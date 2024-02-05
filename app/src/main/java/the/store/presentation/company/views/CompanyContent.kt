package the.store.presentation.company.views

import android.Manifest
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
import com.google.accompanist.permissions.rememberPermissionState
import the.store.presentation.company.models.CompanyUiState
import the.store.utils.DateConvertPatterns
import the.store.utils.checkPermissionState
import the.store.utils.convertToDate
import the.store.utils.imageRequestBuilder

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CompanyContent(
    state: CompanyUiState,
    photoChanged: (Uri) -> Unit,
    deletePhoto: () -> Unit,
    nameChanged: (String) -> Unit,
    descriptionChanged: (String) -> Unit,
) {
    val context = LocalContext.current
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
            .background(TheStoreColors.blackOrWhiteColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .defaultPadding()
                .height(150.dp)
                .background(TheStoreColors.whiteOrBlackColor, baseRoundedCornerShape()),
            contentAlignment = Alignment.Center
        ) {
            val photoUri = state.photoUri
            if (photoUri == null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = rememberVectorPainter(Icons.Filled.Person),
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
                    checkPermissionState(cameraPermissionState, {
                        pickSinglePhoto.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                        pickPhotoEnable = false
                    }, {
                        requestPermissionLauncher.launch(cameraPermissionState.permission)
                        pickPhotoEnable = false
                    }, {
                        showMessage(context, R.string.app_needs_access_to_photo)

                    })
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
                        deletePhoto.invoke()
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
            titleText = stringResource(id = R.string.company_name),
            hintText = stringResource(id = R.string.input_name),
            textValue = state.companyName,
            columnModifier = Modifier.defaultHorizontalPadding(),
            errorMessage = stringResource(id = state.inputErrorState.companyNameErrorState.errorStringRes),
            isError = state.inputErrorState.companyNameErrorState.hasError,
        )

        InputTextField(
            onValueChange = { resultText ->
                descriptionChanged.invoke(resultText)
            },
            titleText = stringResource(id = R.string.company_description),
            hintText = stringResource(id = R.string.input_description),
            textValue = state.description,
            columnModifier = Modifier.defaultHorizontalPadding(),
        )
        if (state.companyCreated) {
            Text(
                text = stringResource(
                    id = R.string.the_latest_update_with_value,
                    state.createdAt.convertToDate()
                ),
                color = TheStoreColors.whiteOrBlackColor,
                modifier = Modifier.defaultHorizontalPadding(),
            )
        }
    }
}