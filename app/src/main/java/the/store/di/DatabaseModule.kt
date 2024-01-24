package the.store.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.db.TheStoreDatabase
import com.example.core.domain.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): TheStoreDatabase {
        return Room.databaseBuilder(
            appContext,
            TheStoreDatabase::class.java,
            Constants.THE_STORE_APP_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}