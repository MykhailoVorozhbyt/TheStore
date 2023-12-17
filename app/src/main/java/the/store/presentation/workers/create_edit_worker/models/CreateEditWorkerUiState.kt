package the.store.presentation.workers.create_edit_worker.models

import the.store.presentation.login_to_app.registration.models.WorkerErrorState

class CreateEditWorkerUiState(
    var name: String = "",
    var surname: String = "",
    var phone: String = "",
    var password: String = "",
    var inputDataErrorState: WorkerErrorState = WorkerErrorState(),
)