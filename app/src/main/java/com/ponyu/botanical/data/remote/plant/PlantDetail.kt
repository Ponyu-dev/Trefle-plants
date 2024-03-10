package com.ponyu.botanical.data.remote.plant

import com.google.gson.annotations.SerializedName

data class PlantDetail(
    @SerializedName("data")
    val data: PlantDetailData
)