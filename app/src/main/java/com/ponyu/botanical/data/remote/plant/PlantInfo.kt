package com.ponyu.botanical.data.remote.plant

import com.google.gson.annotations.SerializedName

data class PlantInfo(
    @SerializedName("data")
    val data: PlantInfoData
)