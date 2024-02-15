package the.store.presentation.primary.models

sealed class PrimaryUiEvent {
    data class Search(val value: String) : PrimaryUiEvent()
}