package the.store.presentation.more.models

sealed class MoreScreenUiEvent{
    object InitUiContent: MoreScreenUiEvent()
    object ExitButtonClick: MoreScreenUiEvent()
}
