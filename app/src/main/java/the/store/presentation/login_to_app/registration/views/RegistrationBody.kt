package the.store.presentation.login_to_app.registration.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.ui.widget.TheStoreOnBackCenterAlignedTopAppBar

@Preview
@Composable
fun RegistrationBodyPreview() {
    RegistrationBody({}, {})
}

@Composable
fun RegistrationBody(
    pressOnBack: () -> Unit = {},
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TheStoreOnBackCenterAlignedTopAppBar(
                com.example.theme.R.string.registration,
                pressOnBack = pressOnBack
            )
        },
        content = { pageContent.invoke(it) }
    )
}