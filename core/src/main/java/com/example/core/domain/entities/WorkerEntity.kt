package com.example.core.domain.entities

import android.net.Uri

data class WorkerEntity(
    val id: Long = 0,
    val photo: Uri?,
    val name: String,
    val surname: String,
    val phone: String,
)