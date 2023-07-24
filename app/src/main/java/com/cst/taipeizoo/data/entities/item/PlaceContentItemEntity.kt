package com.cst.taipeizoo.data.entities.item

import com.cst.taipeizoo.data.network.response.GetAnimalsResponse

sealed class PlaceContentItemEntity {

    data class Header(
        val picUrl: String = "",
        val content: String = "",
        val memo: String = "",
        val category: String = "",
        val webUrl: String = ""
    ): PlaceContentItemEntity()

    data class Animal(
        val data: GetAnimalsResponse.Result.Animal
    ): PlaceContentItemEntity() {
        val picUrl = data.picUrl
        val title = data.nameCh
        val content = data.feature
    }
}