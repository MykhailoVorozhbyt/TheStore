package the.store.presentation.primary.models

sealed class PrimaryUiEvent {
    object SubmitXReportClick : PrimaryUiEvent()
    object SubmitShiftReportClick : PrimaryUiEvent()
    object SubmitCopyReportsClick : PrimaryUiEvent()

}