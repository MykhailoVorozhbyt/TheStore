package the.store.di

import com.example.core.data.dao.products.ProductsDao
import com.example.core.data.dao.worker.WorkerDao
import com.example.core.data.db.TheStoreDatabase
import com.example.core.data.repository.ProductsRepository
import com.example.core.data.repository.ProductsRepositoryImpl
import com.example.core.data.repository.WorkerRepository
import com.example.core.data.repository.WorkerRepositoryImpl
import com.example.core.domain.mapper.DomainMapper
import com.example.core.domain.mapper.DomainMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideDomainMapper(): DomainMapper = DomainMapperImpl()

    @Provides
    fun provideWorkerDao(appDatabase: TheStoreDatabase): WorkerDao =
        appDatabase.workerDao()

    @Provides
    fun provideWorkerRepository(workerDao: WorkerDao): WorkerRepository =
        WorkerRepositoryImpl(workerDao)

    @Provides
    fun provideProductsDao(appDatabase: TheStoreDatabase): ProductsDao =
        appDatabase.productsDao()

    @Provides
    fun provideProductsRepository(productsDao: ProductsDao): ProductsRepository =
        ProductsRepositoryImpl(productsDao)

}