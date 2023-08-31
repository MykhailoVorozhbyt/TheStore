package the.store.presentation.primary

import com.example.core.base.vm.BaseViewModel
import com.example.core.data.repository.WorkerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrimaryViewModel @Inject constructor(
    private val workerRepository: WorkerRepository,
    ) : BaseViewModel() {


    init {
        getWorker()
    }

    private fun getWorker() {
        safeLaunch {
            try {
                val worker = workerRepository.getWorkerById(workerSingleton.getWorker().id)
                println(worker)
            }catch (e:Exception){

            }
        }
    }

}