package com.ponyu.botanical.data.remote.plant

import com.google.gson.annotations.SerializedName

data class Images(
    //    val : List<X>,
    @SerializedName("bark")
    val bark: List<ImagesData?>?,
    @SerializedName("flower")
    val flower: List<ImagesData?>?,
    @SerializedName("fruit")
    val fruit: List<ImagesData?>?,
    @SerializedName("habit")
    val habit: List<ImagesData?>?,
    @SerializedName("leaf")
    val leaf: List<ImagesData?>?,
    @SerializedName("other")
    val other: List<ImagesData?>?
)

data class ImagesData(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("image_url")
    val imageUrl: String?
)