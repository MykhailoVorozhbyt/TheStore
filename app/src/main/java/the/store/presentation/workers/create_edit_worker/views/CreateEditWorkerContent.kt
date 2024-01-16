package the.store.presentation.workers.create_edit_worker.views

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.utils.extensions.modifiers.BaseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultHorizontalPadding
import com.example.core.utils.extensions.modifiers.defaultIconSize
import com.example.core.utils.extensions.modifiers.defaultPadding
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiState


@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview
@Composable
fun CreateEditWorkerContentPreview() {
    CreateEditWorkerContent(CreateEditWorkerUiState(), {}, {}, {}, {}, {}, {})
}


@Composable
fun CreateEditWorkerContent(
    uiState: CreateEditWorkerUiState,
    workerPhotoUri: (Uri) -> Unit,
    workerName: (String) -> Unit,
    workerSurname: (String) -> Unit,
    workerPassword: (String) -> Unit,
    workerPhone: (String) -> Unit,
    workerEmailAddress: (String) -> Unit,
) {
    val context: Context = LocalContext.current

    var pickPhotoEnable by remember {
        mutableStateOf(true)
    }

    val pickSinglePhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                workerPhotoUri.invoke(uri)
            }
            pickPhotoEnable = true
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TheStoreColors.blackOrWhiteColor)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .defaultPadding()
                .height(120.dp)
                .background(TheStoreColors.whiteOrBlackColor, BaseRoundedCornerShape()),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.photoUri == null) {
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
                rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context)
                        .data(uiState.photoUri)
                        .crossfade(true)
                        .build()
                ).let { image ->
                    Image(
                        painter = image,
                        contentDescription = null
                    )
                }
            }

            IconButton(
                onClick = {
                    pickSinglePhoto.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                    pickPhotoEnable = false
                },
                enabled = pickPhotoEnable,
                modifier = Modifier
                    .align(Alignment.TopEnd),
            ) {
                Icon(
                    painter = rememberVectorPainter(Icons.Filled.AddCircle),
                    contentDescription = "worker icon",
                    tint = TheStoreColors.blackOrWhiteColor,
                    modifier = Modifier.defaultIconSize()
                )
            }
        }
        InputTextField(
            onValueChange = { resultText ->
                workerName.invoke(resultText)
            },
            titleText = stringResource(id = R.string.name),
            hintText = stringResource(id = R.string.input_name),
            textValue = uiState.name,
            columnModifier = Modifier.defaultHorizontalPadding(),
            errorMessage = stringResource(id = uiState.inputDataErrorState.nameErrorState.errorStringRes),
            isError = uiState.inputDataErrorState.nameErrorState.hasError,
        )
        InputTextField(
            onValueChange = { resultText ->
                workerSurname.invoke(resultText)
            },
            titleText = stringResource(id = R.string.surname),
            hintText = stringResource(id = R.string.input_surname),
            textValue = uiState.surname,
            columnModifier = Modifier.defaultHorizontalPadding(),
            errorMessage = stringResource(id = uiState.inputDataErrorState.surnameErrorState.errorStringRes),
            isError = uiState.inputDataErrorState.surnameErrorState.hasError,
        )
        InputTextField(
            onValueChange = { resultText ->
                workerPhone.invoke(resultText)
            },
            hintText = stringResource(id = R.string.input_phone),
            titleText = stringResource(id = R.string.phone),
            textValue = uiState.phone,
            columnModifier = Modifier.defaultHorizontalPadding(),
            errorMessage = stringResource(id = uiState.inputDataErrorState.phoneErrorState.errorStringRes),
            isError = uiState.inputDataErrorState.phoneErrorState.hasError,
        )
        InputTextField(
            onValueChange = { resultText ->
                workerPassword.invoke(resultText)
            },
            hintText = stringResource(id = R.string.input_password),
            titleText = stringResource(id = R.string.password),
            textValue = uiState.password,
            columnModifier = Modifier.defaultHorizontalPadding(),
            errorMessage = stringResource(id = uiState.inputDataErrorState.passwordErrorState.errorStringRes),
            isError = uiState.inputDataErrorState.passwordErrorState.hasError,
        )
        InputTextField(
            onValueChange = { resultText ->
                workerEmailAddress.invoke(resultText)
            },
            titleText = stringResource(id = R.string.email_address),
            hintText = stringResource(id = R.string.input_email_address),
            textValue = uiState.emailAddress,
            columnModifier = Modifier.defaultHorizontalPadding(),
            errorMessage = stringResource(id = uiState.inputDataErrorState.emailAddressErrorState.errorStringRes),
            isError = uiState.inputDataErrorState.emailAddressErrorState.hasError,
        )
    }
}