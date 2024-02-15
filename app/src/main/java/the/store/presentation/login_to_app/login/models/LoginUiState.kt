package the.store.presentation.login_to_app.login.models

import androidx.compose.runtime.Immutable
import com.example.core.base.states.BaseUiState
import com.example.core.base.states.ErrorState

@Immutable
data class LoginUiState(
    val phoneValue: String = "",
    val passwordValue: String = "",
    val inputDataErrorState: LoginErrorState = LoginErrorState(),
    val userNotLoggedIn: Boolean = false,
    val userLoggedIn: Boolean = false,
    override val errorState: ErrorState = ErrorState(),
    override val isLoading: Boolean = false,
) : BaseUiState()