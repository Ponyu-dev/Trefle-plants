package com.ponyu.botanical.data.remote.plant

import com.google.gson.annotations.SerializedName

data class FruitOrSeed(
    @SerializedName("conspicuous")
    val conspicuous: Boolean,
    @SerializedName("color")
    val color: List<String>,
    @SerializedName("shape")
    val shape: String,
    @SerializedName("seed_persistence")
    val seedPersistence: Boolean
)