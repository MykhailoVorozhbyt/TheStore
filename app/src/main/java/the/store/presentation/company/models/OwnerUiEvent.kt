package the.store.presentation.company.models

import android.net.Uri

sealed class OwnerUiEvent {
    data object InitUiContent : OwnerUiEvent()
    data class PhotoChanged(val uri: Uri) : OwnerUiEvent()
    data object DeletePhotoUri : OwnerUiEvent()
    data class CompanyNameChanged(val name: String) : OwnerUiEvent()
    data class DescriptionChanged(val description: String) : OwnerUiEvent()
    data object SubmitEditClick : OwnerUiEvent()
}