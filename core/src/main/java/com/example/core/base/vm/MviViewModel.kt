package com.example.core.base.vm

import com.example.core.base.states.BaseViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

abstract class MviViewModel<STATE : BaseViewState<*>, EVENT> : BaseViewModel() {
    private val _uiState = MutableStateFlow<BaseViewState<*>>(BaseViewState.Empty)
    val uiState = _uiState.asStateFlow()

    abstract fun onTriggerEvent(eventType: EVENT)

    protected fun setState(state: STATE) = safeLaunch {
        _uiState.emit(state)
    }

    override fun startLoading() {
        super.startLoading()
        _uiState.value = BaseViewState.Loading
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        _uiState.value = BaseViewState.Error(exception)
    }

    //    protected suspend inline fun <reified T : BaseViewState<*>> getCurrentState(): Any = suspendCoroutine { continuation ->
//        safeLaunch {
//            val castState = uiState.filterIsInstance<BaseViewState.Data<*>>()
//                .map { it.value }
//                .first()
//            continuation.resume(castState as T)
//        }
//    }

//    suspend inline fun <reified T : Any> getCurrentState() =
//        uiState.filterIsInstance<BaseViewState.Data<*>>()
//            .map { it.value }.first()
}