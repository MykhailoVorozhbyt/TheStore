package the.store.domain.mapper

import android.net.Uri
import com.example.core.domain.db_entity.WorkerDbEntity
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiState

fun WorkerDbEntity.mapToCreateEditWorkerUiState(): CreateEditWorkerUiState =
    CreateEditWorkerUiState(
        id = id,
        photoUri = Uri.parse(photoUri),
        name = name,
        surname = surname,
        phone = phone,
        password = password,
        emailAddress = emailAddress,
    )