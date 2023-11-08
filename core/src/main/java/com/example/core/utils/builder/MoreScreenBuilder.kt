package com.example.core.utils.builder

import android.content.Context
import com.example.core.domain.more_screen_dto.BaseMoreScreenItemDto
import com.example.core.domain.more_screen_dto.MoreScreenButton
import com.example.core.domain.more_screen_dto.MoreScreenTitleDto
import com.example.core.utils.enums.MoreScreenClickAction
import com.example.theme.R

class MoreScreenBuilder(
    private val context: Context
) {

    fun build(): List<BaseMoreScreenItemDto> {
        val resultList: MutableList<BaseMoreScreenItemDto> = mutableListOf()

        resultList.add(buildMainInformationTitle())
        resultList.add(buildProfileButton())

        resultList.add(buildAdditionalFunctionTitle())
        resultList.add(buildCopyTheDeviceIdButton())

        resultList.add(buildAppVersionTitle())

        return resultList
    }


    private fun buildMainInformationTitle(): BaseMoreScreenItemDto {
        return MoreScreenTitleDto(
            text = context.getString(R.string.main_information),
            textStyle = R.style.PrimaryTextStyle16Bold,
        )
    }

    private fun buildProfileButton(): BaseMoreScreenItemDto {
        return MoreScreenButton(
            icon = R.drawable.ic_person,
            iconColor = R.color.black,
            clickAction = MoreScreenClickAction.UserProfileClick,
            text = context.getString(R.string.profile),
            textStyle = null,
        )
    }


    private fun buildAdditionalFunctionTitle(): BaseMoreScreenItemDto {
        return MoreScreenTitleDto(
            text = context.getString(R.string.additional_function),
            textStyle = R.style.PrimaryTextStyle16Bold,
        )
    }

    private fun buildCopyTheDeviceIdButton(): BaseMoreScreenItemDto {
        return MoreScreenButton(
            icon = androidx.appcompat.R.drawable.abc_ic_menu_copy_mtrl_am_alpha,
            iconColor = R.color.black,
            clickAction = MoreScreenClickAction.CopyTheDeviceIdClick,
            text = context.getString(R.string.copy_the_device_id),
            textStyle = null,
        )
    }

    private fun buildAppVersionTitle(): BaseMoreScreenItemDto {
        return MoreScreenTitleDto(
            text = context.getString(R.string.version_with_value, "1.0"),
            textStyle = R.style.PrimaryTextStyle14Bold,
        )
    }
}