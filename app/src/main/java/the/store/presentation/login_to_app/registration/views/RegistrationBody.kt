package the.store.presentation.login_to_app.registration.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.core.ui.widget.TheStoreToolbar

@Composable
fun RegistrationBody(
    pressOnBack: () -> Unit = {},
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TheStoreToolbar(
                com.example.theme.R.string.registration,
                pressOnBack = pressOnBack
            )
        },
        content = { pageContent.invoke(it) }
    )
}