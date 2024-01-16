package the.store.presentation.workers.create_edit_worker.models

import android.net.Uri

sealed class CreateEditWorkerUiEvent {
    data class InitUiContent(val workerId: Long = 0) : CreateEditWorkerUiEvent()

    data class PhotoChanged(val uri: Uri) : CreateEditWorkerUiEvent()
    data class NameChanged(val name: String) : CreateEditWorkerUiEvent()
    data class SurnameChanged(val surname: String) : CreateEditWorkerUiEvent()
    data class PhoneChanged(val phone: String) : CreateEditWorkerUiEvent()
    data class PasswordChanged(val password: String) : CreateEditWorkerUiEvent()
    data class EmailAddressChanged(val email: String) : CreateEditWorkerUiEvent()

    object SubmitCreateEditClick : CreateEditWorkerUiEvent()
}