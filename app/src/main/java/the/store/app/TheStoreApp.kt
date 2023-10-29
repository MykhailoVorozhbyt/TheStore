package the.store.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TheStoreApp : Application() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityLifecycleHandler())
    }
}
