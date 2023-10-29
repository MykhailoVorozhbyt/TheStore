package the.store.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log

class ActivityLifecycleHandler() :
    Application.ActivityLifecycleCallbacks {

    @SuppressLint("LogNotTimber")
    override fun onActivityPaused(p0: Activity) {
        Log.d(TAG, "onActivityPaused at ${p0.localClassName}")
    }

    @SuppressLint("LogNotTimber")
    override fun onActivityStarted(p0: Activity) {
        Log.d(TAG, "onActivityStarted at ${p0.localClassName}")
    }

    @SuppressLint("LogNotTimber")
    override fun onActivityDestroyed(p0: Activity) {
        Log.d(TAG, "onActivityDestroyed at ${p0.localClassName}")
    }

    @SuppressLint("LogNotTimber")
    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        Log.d(TAG, "onActivitySaveInstanceState at ${p0.localClassName}")
    }

    @SuppressLint("LogNotTimber")
    override fun onActivityStopped(p0: Activity) {
        Log.d(TAG, "onActivityStopped at ${p0.localClassName}")
    }

    @SuppressLint("LogNotTimber")
    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        p0.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Log.d(TAG, "onActivityCreated at ${p0.localClassName}")
    }

    @SuppressLint("LogNotTimber")
    override fun onActivityResumed(p0: Activity) {
        Log.d(TAG, "onActivityResumed at ${p0.localClassName}")
    }

    companion object {
        private const val TAG = "LifecycleCallbacks"
    }
}