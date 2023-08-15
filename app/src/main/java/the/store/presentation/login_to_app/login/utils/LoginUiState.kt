package the.store.presentation.login_to_app.login.utils

import com.example.core.base.states.BaseState

data class LoginUiState(
    var phoneValue: String = "",
    var passwordValue: String = "",
    var inputDataErrorState: LoginErrorState = LoginErrorState(),
    var userNotLoggedIn: Boolean = false,
    var userLoggedIn: Boolean = false,
) : BaseState()