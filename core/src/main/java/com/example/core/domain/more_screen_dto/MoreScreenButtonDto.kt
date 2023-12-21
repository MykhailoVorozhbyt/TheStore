package com.example.core.domain.more_screen_dto

import androidx.annotation.DrawableRes
import com.example.core.utils.enums.MoreScreenClickAction

data class MoreScreenButtonDto(
    @DrawableRes
    val icon: Int,
    val clickAction: MoreScreenClickAction,
    override val text: String,
    override val textStyle: Int?,
) : BaseMoreScreenTextDto()