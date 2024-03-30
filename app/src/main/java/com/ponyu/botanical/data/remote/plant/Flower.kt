package com.ponyu.botanical.data.remote.plant

import com.google.gson.annotations.SerializedName

data class Flower(
    @SerializedName("color")
    val color: List<String?>?,
    @SerializedName("conspicuous")
    val conspicuous: Boolean?
)