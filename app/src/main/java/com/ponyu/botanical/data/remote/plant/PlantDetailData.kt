package com.ponyu.botanical.data.remote.plant

import com.google.gson.annotations.SerializedName

data class PlantDetailData(
    @SerializedName("main_species")
    val mainSpecies: MainSpecies,
)