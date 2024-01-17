package the.store.presentation.login_to_app.registration.models

import android.os.Parcelable
import com.example.core.base.states.FieldErrorState
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkerErrorState(
    val nameErrorState: FieldErrorState = FieldErrorState(),
    val surnameErrorState: FieldErrorState = FieldErrorState(),
    val phoneErrorState: FieldErrorState = FieldErrorState(),
    val passwordErrorState: FieldErrorState = FieldErrorState(),
    val emailAddressErrorState: FieldErrorState = FieldErrorState(),
): Parcelable
