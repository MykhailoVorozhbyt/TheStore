package the.store.presentation.workers.create_edit_worker.models

import com.example.core.domain.models.db_entity.WorkerEntity
import the.store.presentation.login_to_app.registration.models.WorkerErrorState

data class CreateEditWorkerUiState(
    var id: Long = 0,
    var name: String = "",
    var surname: String = "",
    var phone: String = "",
    var password: String = "",
    var emailAddress: String = "",
    var inputDataErrorState: WorkerErrorState = WorkerErrorState(),
) {
    fun mapToWorkerEntity(): WorkerEntity =
        WorkerEntity(
            name = name,
            surname = surname,
            password = password,
            phone = phone,
            emailAddress = emailAddress
        )
}