package the.store.di

import android.content.Context
import android.content.SharedPreferences
import com.example.core.domain.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import the.store.app.TheStoreApp
import the.store.utils.helpes.preference.PreferenceHelper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {


    @Singleton
    @Provides
    fun provideApplication(): TheStoreApp {
        return TheStoreApp()
    }


    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.THE_STORE_APP_PREFERENCES, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providePreferenceHelper(preference: SharedPreferences): PreferenceHelper {
        return PreferenceHelper(preference)
    }

}