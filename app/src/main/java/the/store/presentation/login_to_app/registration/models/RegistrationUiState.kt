package the.store.presentation.login_to_app.registration.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegistrationUiState(
    var name: String = "",
    var surname: String = "",
    var phone: String = "",
    var password: String = "",
    var inputDataErrorState: WorkerErrorState = WorkerErrorState(),
    var isRegister: Boolean = false
) : Parcelable

