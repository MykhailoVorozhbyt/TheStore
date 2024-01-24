package the.store.presentation.login_to_app.login.models

import androidx.compose.runtime.Immutable
import com.example.core.base.states.BaseUiState
import com.example.core.base.states.ErrorState

@Immutable
data class LoginUiState(
    var phoneValue: String = "",
    var passwordValue: String = "",
    var inputDataErrorState: LoginErrorState = LoginErrorState(),
    var userNotLoggedIn: Boolean = false,
    var userLoggedIn: Boolean = false,
    override val errorState: ErrorState = ErrorState(),
    override val isLoading: Boolean = false,
) : BaseUiState()