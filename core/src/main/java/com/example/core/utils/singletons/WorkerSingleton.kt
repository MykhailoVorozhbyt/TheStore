package com.example.core.utils.singletons

import com.example.core.domain.constants.Constants
import com.example.core.domain.models.db_entity.WorkerEntity
import com.example.core.utils.enums.PreferenceKey
import com.example.core.utils.helpers.PreferenceHelper
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkerSingleton @Inject constructor(private val preferenceHelper: PreferenceHelper) {

    private var instance: WorkerEntity? = null

    fun getWorker(): WorkerEntity {
        if (instance == null) {
            instance = Gson().fromJson(
                preferenceHelper.getDataByKey(
                    PreferenceKey.CurrentWorker, Constants.EMPTY_STRING
                ), WorkerEntity::class.java
            )
        }
        return instance as WorkerEntity
    }

    fun setWorker(config: WorkerEntity) {
        preferenceHelper.setDataByKey(
            PreferenceKey.CurrentWorker, Gson().toJson(config)
        )
        instance = null
    }
}