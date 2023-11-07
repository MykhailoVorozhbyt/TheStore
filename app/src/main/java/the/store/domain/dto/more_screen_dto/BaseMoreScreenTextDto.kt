package the.store.domain.dto.more_screen_dto

abstract class BaseMoreScreenTextDto : BaseMoreScreenItemDto() {
    abstract val text: String
    abstract val textStyle: Int?
}