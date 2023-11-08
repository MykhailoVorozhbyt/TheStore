package the.store.presentation.more

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.viewModelScope
import com.example.core.base.vm.BaseStateViewModel
import com.example.core.domain.more_screen_dto.BaseMoreScreenItemDto
import com.example.core.utils.builder.MoreScreenBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.inject.Inject


@Parcelize
data class MoreScreenUiState(
    val isLoading: Boolean = false,
    val screenUi: @RawValue List<BaseMoreScreenItemDto> = listOf(),
) : Parcelable

sealed class MoreScreenUiEvent

@HiltViewModel
class MoreScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : BaseStateViewModel<MoreScreenUiState, MoreScreenUiEvent>(MoreScreenUiState()) {

    init {
        buildUI()
    }

    private fun buildUI() {
        viewModelScope.launch {
            val ui = MoreScreenBuilder(context).build()
        }
    }

    override fun onTriggerEvent(eventType: MoreScreenUiEvent) {
        TODO("Not yet implemented")
    }

}