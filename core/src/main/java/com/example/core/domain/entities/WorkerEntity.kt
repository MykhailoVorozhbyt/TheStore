package com.example.core.domain.entities

import android.graphics.Bitmap

data class WorkerEntity(
    val id: Long = 0,
    val photo: Bitmap?,
    val name: String,
    val surname: String,
    val phone: String,
)