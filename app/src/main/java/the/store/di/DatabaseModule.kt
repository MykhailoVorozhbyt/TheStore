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
import the.store.utils.migration.MIGRATION_1_2
import the.store.utils.migration.MIGRATION_2_3
import the.store.utils.migration.MIGRATION_3_4
import the.store.utils.migration.MIGRATION_4_5
import the.store.utils.migration.MIGRATION_5_6
import the.store.utils.migration.MIGRATION_6_7
import the.store.utils.migration.MIGRATION_7_8
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
            .addMigrations(
                MIGRATION_1_2,
                MIGRATION_2_3,
                MIGRATION_3_4,
                MIGRATION_4_5,
                MIGRATION_5_6,
                MIGRATION_6_7,
                MIGRATION_7_8
            )
            .build()
    }
}