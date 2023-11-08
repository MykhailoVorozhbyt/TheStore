package com.example.core.domain.more_screen_dto

abstract class BaseMoreScreenTextDto : BaseMoreScreenItemDto() {
    abstract val text: String
    abstract val textStyle: Int?
}