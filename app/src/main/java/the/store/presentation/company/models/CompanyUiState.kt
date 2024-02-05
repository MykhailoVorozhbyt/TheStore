package the.store.presentation.company.models

import android.net.Uri

data class CompanyUiState(
    val id: Long = 0,
    val photoUri: Uri? = null,
    val companyName: String = "",
    val description: String = "",
    val inputErrorState: OwnerErrorState = OwnerErrorState(),
    val companyCreated: Boolean = false,
    val companyUpdate: Boolean = false,
    val createdAt: Long = 0L
)
