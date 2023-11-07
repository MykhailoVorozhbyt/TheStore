package the.store.utils.builder

import android.content.Context
import the.store.domain.dto.more_screen_dto.BaseMoreScreenItemDto
import the.store.domain.dto.more_screen_dto.MoreScreenButton
import the.store.utils.extensions.enums.MoreScreenClickAction

class MoreScreenBuilder(
    private val context: Context
) {

    fun build(): List<BaseMoreScreenItemDto> {
        val resultList: MutableList<BaseMoreScreenItemDto> = mutableListOf()

        resultList.add(buildProfileButton())

        return resultList
    }


    private fun buildProfileButton(): BaseMoreScreenItemDto {
        return MoreScreenButton(
            icon = com.example.theme.R.drawable.ic_person,
            iconColor = com.example.theme.R.color.black,
            clickAction = MoreScreenClickAction.UserProfileClick,
            text = context.getString(com.example.theme.R.string.profile),
            textStyle = null,
        )
    }

}