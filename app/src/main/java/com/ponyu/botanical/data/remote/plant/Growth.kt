package com.ponyu.botanical.data.remote.plant

import com.google.gson.annotations.SerializedName

data class Growth(
    @SerializedName("description")
    val description: String,
    @SerializedName("sowing")
    val sowing: String,
    @SerializedName("days_to_harvest")
    val daysToHarvest: Float,
    @SerializedName("row_spacing")
    val rowSpacing: PlantHeightCm,
    @SerializedName("spread")
    val spread: PlantHeightCm,
    @SerializedName("ph_maximum")
    val phMaximum: Float,
    @SerializedName("ph_minimum")
    val phMinimum: Float,
    @SerializedName("light")
    val light: Int,
    @SerializedName("atmospheric_humidity")
    val atmosphericHumidity: Int,
    @SerializedName("growth_months")
    val growthMonths: List<String>,
    @SerializedName("bloom_months")
    val bloomMonths: List<String>,
    @SerializedName("fruit_months")
    val fruitMonths: List<String>,
    @SerializedName("minimum_precipitation")
    val minimumPrecipitation: PlantHeightMm,
    @SerializedName("maximum_precipitation")
    val maximumPrecipitation: PlantHeightMm,
    @SerializedName("minimum_root_depth")
    val minimumRootDepth: PlantHeightCm,
    @SerializedName("minimum_temperature")
    val minimumTemperature: TemperatureData,
    @SerializedName("maximum_temperature")
    val maximumTemperature: TemperatureData,
    @SerializedName("soil_nutriments")
    val soilNutriments: Int,
    @SerializedName("soil_salinity")
    val soilSalinity: Int,
    @SerializedName("soil_texture")
    val soilTexture: Int,
    @SerializedName("soil_humidity")
    val soilHumidity: Int,
)

data class TemperatureData(
    @SerializedName("deg_f")
    val degF: String,
    @SerializedName("deg_c")
    val degC: String
)
