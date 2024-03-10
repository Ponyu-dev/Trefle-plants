package com.ponyu.botanical.data.remote.plant

import com.google.gson.annotations.SerializedName

data class PlantHeightCm(
    @SerializedName("cm")
    val cm: String
)

data class PlantHeightMm(
    @SerializedName("mm")
    val mm: String
)