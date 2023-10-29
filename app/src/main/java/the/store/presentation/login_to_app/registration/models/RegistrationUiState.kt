package the.store.presentation.login_to_app.registration.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegistrationUiState(
    var name: String = "",
    var surname: String = "",
    var phone: String = "",
    var password: String = "",
    var inputDataErrorState: RegistrationErrorState = RegistrationErrorState(),
    var isRegister: Boolean = false
) : Parcelable

