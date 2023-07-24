package com.cst.taipeizoo.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetAnimalsResponse(
    @SerializedName("result")
    val result: Result
) : Parcelable {

    @Parcelize
    data class Result(
        @SerializedName("results")
        val animals: List<Animal>
    ) : Parcelable {

        @Parcelize
        data class Animal(
            @SerializedName("a_behavior")
            val behavior: String,
            @SerializedName("a_class")
            val clazz: String,
            @SerializedName("a_distribution")
            val distribution: String,
            @SerializedName("a_family")
            val family: String,
            @SerializedName("a_feature")
            val feature: String,
            @SerializedName("a_location")
            val location: String,
            @SerializedName("a_name_ch")
            val nameCh: String,
            @SerializedName("a_name_latin")
            val nameLatin: String,
            @SerializedName("a_order")
            val order: String,
            @SerializedName("a_phylum")
            val phylum: String,
            @SerializedName("a_pic01_url")
            val picUrl: String,
            @SerializedName("a_update")
            val update: String
        ) : Parcelable
    }
}