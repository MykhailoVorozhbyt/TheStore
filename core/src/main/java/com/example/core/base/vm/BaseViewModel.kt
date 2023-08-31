package com.example.core.base.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.singletons.WorkerSingleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var workerSingleton: WorkerSingleton

    protected fun safeLaunch(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(context = context, block = block)
    }

    open fun startLoading() {}
    open fun handleError(exception: Throwable) {}

}