package the.store.utils.validations

import com.example.core.utils.helpers.emailEmptyErrorState
import com.example.core.utils.helpers.emptyFieldErrorState
import com.example.core.utils.helpers.incorrectPhoneErrorState
import com.example.core.utils.helpers.invalidateEmailAddressErrorState
import com.example.core.utils.helpers.passwordEmptyErrorState
import com.example.core.utils.helpers.passwordLengthErrorState
import com.example.core.utils.helpers.phoneEmptyErrorState
import com.example.core.utils.isValidUkrainianPhoneNumber
import com.example.core.utils.mailValidation
import the.store.presentation.login_to_app.registration.models.WorkerErrorState

fun isNameValid(name: String): WorkerErrorState {
    if (name.isBlank()) {
        return WorkerErrorState(nameErrorState = emptyFieldErrorState)
    }
    return WorkerErrorState()
}

fun isSurnameValid(name: String): WorkerErrorState {
    if (name.isBlank()) {
        return WorkerErrorState(surnameErrorState = emptyFieldErrorState)
    }
    return WorkerErrorState()
}

fun isPasswordValid(password: String): WorkerErrorState {
    if (password.isBlank()) {
        return WorkerErrorState(passwordErrorState = passwordEmptyErrorState)
    }
    if (password.length <= 4) {
        return WorkerErrorState(passwordErrorState = passwordLengthErrorState)
    }
    return WorkerErrorState()
}

fun isPhoneValid(phone: String): WorkerErrorState {
    if (phone.isBlank()) {
        return WorkerErrorState(phoneErrorState = phoneEmptyErrorState)
    }
    if (isValidUkrainianPhoneNumber(phone).not()) {
        return WorkerErrorState(
            phoneErrorState = incorrectPhoneErrorState
        )
    }
    return WorkerErrorState()
}

fun isValidEmail(email: String): WorkerErrorState {
    if (email.isBlank()) {
        return WorkerErrorState(emailAddressErrorState = emailEmptyErrorState)
    }
    if (mailValidation(email).not()) {
        return WorkerErrorState(
            emailAddressErrorState = invalidateEmailAddressErrorState
        )
    }
    return WorkerErrorState()
}

fun workerValidateInputs(
    name: String,
    surname: String,
    phone: String,
    password: String,
    email: String
): WorkerErrorState {
    val nameValidate: WorkerErrorState = isNameValid(name)
    val surnameValidate: WorkerErrorState = isSurnameValid(surname)
    val phoneValidate: WorkerErrorState = isPhoneValid(phone)
    val passwordValidate: WorkerErrorState = isPasswordValid(password)
    val emailValidate: WorkerErrorState = isValidEmail(email)
    return when {
        nameValidate.nameErrorState.hasError -> nameValidate
        surnameValidate.surnameErrorState.hasError -> surnameValidate
        phoneValidate.phoneErrorState.hasError -> phoneValidate
        passwordValidate.passwordErrorState.hasError -> passwordValidate
        emailValidate.emailAddressErrorState.hasError -> emailValidate
        else -> WorkerErrorState()
    }
}