package the.store.presentation.more.models

import android.os.Parcelable
import com.example.core.domain.more_screen_dto.BaseMoreScreenItemDto
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class MoreScreenUiState(
    val isLoading: Boolean = false,
    val screenUi: @RawValue List<BaseMoreScreenItemDto> = listOf(),
    val isExitButtonClicked: Boolean = false
) : Parcelable
