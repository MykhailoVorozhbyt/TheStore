package the.store.presentation.primary.models

import com.example.core.base.states.BaseUiState
import com.example.core.base.states.ErrorState

data class PrimaryUiState(
    override val errorState: ErrorState = ErrorState(),
    override val isLoading: Boolean = false
) : BaseUiState()
