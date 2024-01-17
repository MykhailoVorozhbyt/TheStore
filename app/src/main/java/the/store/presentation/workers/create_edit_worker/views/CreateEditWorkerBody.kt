package the.store.presentation.workers.create_edit_worker.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.ui.widget.TheStoreOnActionToolbar

@Preview
@Composable
fun CreateEditWorkerBodyPreview() {
    CreateEditWorkerBody({}, {}) {}
}

@Composable
fun CreateEditWorkerBody(
    pressOnBack: () -> Unit = {},
    pressEditCreate: () -> Unit,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            TheStoreOnActionToolbar(
                com.example.theme.R.string.worker,
                pressOnBack = pressOnBack,
                pressEditCreate = pressEditCreate
            )
        },
        content = { pageContent.invoke(it) }
    )
}