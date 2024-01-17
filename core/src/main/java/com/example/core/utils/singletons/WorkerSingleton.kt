package com.example.core.utils.singletons

import com.example.core.domain.constants.Constants
import com.example.core.domain.db_entity.WorkerDbEntity
import com.example.core.utils.enums.PreferenceKey
import com.example.core.utils.helpers.PreferenceHelper
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkerSingleton @Inject constructor(private val preferenceHelper: PreferenceHelper) {

    private var instance: WorkerDbEntity? = null

    fun getWorker(): WorkerDbEntity {
        if (instance == null) {
            instance = Gson().fromJson(
                preferenceHelper.getDataByKey(
                    PreferenceKey.CurrentWorker, Constants.EMPTY_STRING
                ), WorkerDbEntity::class.java
            )
        }
        return instance as WorkerDbEntity
    }

    fun setWorker(config: WorkerDbEntity) {
        preferenceHelper.setDataByKey(
            PreferenceKey.CurrentWorker, Gson().toJson(config)
        )
        instance = null
    }
}