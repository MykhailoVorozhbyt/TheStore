package the.store.presentation.workers.create_edit_worker.models

import android.net.Uri
import androidx.annotation.StringRes
import com.example.core.domain.db_entity.WorkerDbEntity
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
    @StringRes
    val userDoneNotification: Int = 0,

    ) {
    fun mapToWorkerEntity(): WorkerDbEntity {
        val photoUri = if (this.photoUri == null) null else this.photoUri.toString()
        return WorkerDbEntity(
            id = id,
            name = name,
            photoUri = photoUri,
            surname = surname,
            password = password,
            phone = phone,
            emailAddress = emailAddress
        )
    }
}

