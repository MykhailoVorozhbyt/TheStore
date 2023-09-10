package the.store.presentation.primary.views.bottom_sheet_views

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.utils.extensions.modifiers.defaultHorizontalPadding
import com.example.theme.R
import the.store.presentation.primary.PrimaryViewModel

@Composable
fun BottomSheetSearchView(viewModel: PrimaryViewModel) {
    InputTextField(
        onValueChange = { resultText ->
//                            viewModel.onTriggerEvent(
//                                LoginUiEvent.PhoneChanged(
//                                    resultText
//                                )
//                            )
        },
//                        textValue = uiState.phoneValue,
        titleText = stringResource(id = R.string.input_data),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//                        errorMessage = stringResource(id = uiState.inputDataErrorState.phoneErrorState.errorMessageStringResource),
//                        isError = uiState.inputDataErrorState.phoneErrorState.hasError,
        columnModifier = Modifier.defaultHorizontalPadding()

    )
}