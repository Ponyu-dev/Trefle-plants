package com.ponyu.botanical.data.remote.plant

import com.google.gson.annotations.SerializedName

data class PlantInfoData(
    @SerializedName("main_species")
    val mainSpecies: MainSpecies,
)