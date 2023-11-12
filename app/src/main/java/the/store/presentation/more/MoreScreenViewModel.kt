package the.store.presentation.more

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.core.base.vm.BaseStateViewModel
import com.example.core.utils.builder.MoreScreenBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import the.store.presentation.more.models.MoreScreenUiEvent
import the.store.presentation.more.models.MoreScreenUiState
import javax.inject.Inject


@HiltViewModel
class MoreScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : BaseStateViewModel<MoreScreenUiState, MoreScreenUiEvent>(MoreScreenUiState()) {

    override fun onTriggerEvent(eventType: MoreScreenUiEvent) {
        when (eventType) {
            is MoreScreenUiEvent.InitUiContent -> initUiContent()
        }
    }

    private fun initUiContent() {
        setState(MoreScreenUiState(isLoading = true))
        viewModelScope.launch {
            val ui = MoreScreenBuilder(context).build()
            setState(
                uiState.value.copy(
                    isLoading = false,
                    screenUi = ui
                )
            )
        }
    }

}