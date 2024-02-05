package the.store.utils.validations

import com.example.core.utils.helpers.emptyFieldErrorState
import the.store.presentation.company.models.OwnerErrorState

fun isCompanyNameValid(name: String): OwnerErrorState {
    if (name.isBlank()) {
        return OwnerErrorState(companyNameErrorState = emptyFieldErrorState)
    }
    return OwnerErrorState()
}
