package the.store.presentation.more

import com.example.core.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreScreenViewModel @Inject constructor() : BaseViewModel() {

    init {
        buildUI()
    }

    private fun buildUI() {
//        val ui = MoreScreenBuilder().build()
    }

}