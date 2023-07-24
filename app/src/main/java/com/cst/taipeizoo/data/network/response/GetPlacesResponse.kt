package com.cst.taipeizoo.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetPlacesResponse(
    @SerializedName("result")
    val result: Result
) : Parcelable {

    @Parcelize
    data class Result(
        @SerializedName("results")
        val places: List<Place>
    ) : Parcelable {

        @Parcelize
        data class Place(
            @SerializedName("e_category")
            val category: String,
            @SerializedName("e_info")
            val info: String,
            @SerializedName("e_memo")
            val memo: String,
            @SerializedName("e_name")
            val name: String,
            @SerializedName("e_pic_url")
            val picUrl: String,
            @SerializedName("e_url")
            val url: String,
            @SerializedName("_id")
            val id: Int
        ) : Parcelable
    }
}