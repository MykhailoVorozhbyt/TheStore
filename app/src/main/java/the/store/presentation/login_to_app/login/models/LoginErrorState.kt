package the.store.presentation.login_to_app.login.models

import com.example.core.base.states.FieldErrorState
import com.example.theme.R

data class LoginErrorState(
    val phoneErrorState: FieldErrorState = FieldErrorState(),
    val passwordErrorState: FieldErrorState = FieldErrorState()
)
