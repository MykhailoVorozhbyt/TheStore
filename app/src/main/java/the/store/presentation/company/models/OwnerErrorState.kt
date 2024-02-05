package the.store.presentation.company.models

import com.example.core.base.states.BaseErrorState
import com.example.core.base.states.FieldErrorState

data class OwnerErrorState(
    val companyNameErrorState: FieldErrorState = FieldErrorState(),
) : BaseErrorState()