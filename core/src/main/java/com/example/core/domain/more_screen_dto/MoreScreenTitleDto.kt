package com.example.core.domain.more_screen_dto


data class MoreScreenTitleDto(
    override val text: String,
    override val textStyle: Int
) : BaseMoreScreenTextDto()