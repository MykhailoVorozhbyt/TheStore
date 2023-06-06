package the.store.presentation.splash

import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimatedSplashScreenViewModel : BaseViewModel() {

    private val mutableStateFlow = MutableStateFlow(true)
    val isLoading = mutableStateFlow.asStateFlow()

    init {
        initVM()
    }

    private fun initVM() {
        viewModelScope.launch {
            delay(2000)
            mutableStateFlow.value = false
        }
    }

}