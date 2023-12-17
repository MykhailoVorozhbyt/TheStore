package the.store.presentation.workers.models

sealed class WorkersUiEvent {
    data class InputValueChanged(val inputValue: String) : WorkersUiEvent()
    object InitUiScreen: WorkersUiEvent()
}