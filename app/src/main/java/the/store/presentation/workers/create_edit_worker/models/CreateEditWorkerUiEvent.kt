package the.store.presentation.workers.create_edit_worker.models

sealed class CreateEditWorkerUiEvent {
    object InitUI: CreateEditWorkerUiEvent()

    data class NameChanged(val name: String) : CreateEditWorkerUiEvent()
    data class SurnameChanged(val surname: String) : CreateEditWorkerUiEvent()
    data class PhoneChanged(val phone: String) : CreateEditWorkerUiEvent()
    data class PasswordChanged(val password: String) : CreateEditWorkerUiEvent()

    object SubmitCreateEditClick : CreateEditWorkerUiEvent()
}