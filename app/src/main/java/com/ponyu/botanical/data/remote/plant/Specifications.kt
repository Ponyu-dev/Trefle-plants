package com.ponyu.botanical.data.remote.plant

import com.google.gson.annotations.SerializedName

data class Specifications(
    @SerializedName("ligneous_type")
    val ligneousType: String,
    @SerializedName("growth_form")
    val growthForm: String,
    @SerializedName("growth_habit")
    val growthHabit: String,
    @SerializedName("growth_rate")
    val growthRate: String,
    @SerializedName("average_height")
    val averageHeight: PlantHeightCm,
    @SerializedName("maximum_height")
    val maximumHeight: PlantHeightCm,
    @SerializedName("nitrogen_fixation")
    val nitrogenFixation: String,
    @SerializedName("shape_and_orientation")
    val shapeAndOrientation: String,
    @SerializedName("toxicity")
    val toxicity: String
)
