package com.example.core.domain.more_screen_dto

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import the.store.utils.extensions.enums.MoreScreenClickAction

data class MoreScreenButton(
    @DrawableRes
    val icon: Int,
    @ColorRes
    val iconColor: Int,
    val clickAction: MoreScreenClickAction,
    override val text: String,
    override val textStyle: Int?,
) : BaseMoreScreenTextDto()