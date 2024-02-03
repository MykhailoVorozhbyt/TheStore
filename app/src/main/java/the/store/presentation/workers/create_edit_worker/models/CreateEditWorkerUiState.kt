package the.store.presentation.workers.create_edit_worker.models

import android.net.Uri
import the.store.presentation.login_to_app.registration.models.WorkerErrorState

data class CreateEditWorkerUiState(
    val id: Long = 0,
    val photoUri: Uri? = null,
    val name: String = "",
    val surname: String = "",
    val phone: String = "",
    val password: String = "",
    val emailAddress: String = "",
    val inputDataErrorState: WorkerErrorState = WorkerErrorState(),
    val actionProduct: Boolean = false,
    val deleteProduct: Boolean = false
)

