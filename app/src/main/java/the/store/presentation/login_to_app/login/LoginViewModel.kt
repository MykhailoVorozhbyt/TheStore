package the.store.presentation.login_to_app.login

import com.example.core.base.BaseViewModel
import com.example.core.data.repository.WorkerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val workerRepository: WorkerRepository
) : BaseViewModel(){



}