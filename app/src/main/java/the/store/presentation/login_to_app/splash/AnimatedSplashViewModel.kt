package the.store.presentation.login_to_app.splash

import androidx.compose.runtime.mutableStateOf
import com.example.core.base.BaseViewModel
import com.example.core.utils.enums.PreferenceKey
import dagger.hilt.android.lifecycle.HiltViewModel
import the.store.utils.helpes.preference.PreferenceHelper
import javax.inject.Inject

@HiltViewModel
class AnimatedSplashViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper
) : BaseViewModel() {

    var userIsLoggedIn = mutableStateOf(false)

    init {
        checkUserIsLoggedIn()
    }

    private fun checkUserIsLoggedIn() {
        userIsLoggedIn.value = preferenceHelper.getDataByKey(PreferenceKey.UserIsLoggedIn, false)
    }

}