package the.store.di

import com.example.core.data.dao.WorkerDao
import com.example.core.data.db.TheStoreDatabase
import com.example.core.data.repository.WorkerRepository
import com.example.core.data.repository.WorkerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideWorkerDao(appDatabase: TheStoreDatabase): WorkerDao {
        return appDatabase.workerDao()
    }

    @Provides
    fun provideWorkerRepository(workerDao: WorkerDao): WorkerRepository =
        WorkerRepositoryImpl(workerDao)

}