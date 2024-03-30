package com.ponyu.botanical.data.remote.plant

import com.google.gson.annotations.SerializedName

data class Foliage(
    @SerializedName("texture")
    val texture: String?,
    @SerializedName("color")
    val color: List<String?>?,
    @SerializedName("leaf_retention")
    val leafRetention: Boolean?
)