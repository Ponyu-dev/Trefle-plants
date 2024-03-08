package com.ponyu.botanical.data.remote

import com.google.gson.annotations.SerializedName

data class ListPlants(
    @SerializedName("data")
    val data: List<PlantData>,
    @SerializedName("meta")
    val meta: Meta
)

data class PlantData(
    val id: Int,
    val common_name: String,
    val image_url: String,
    val status: String,
    val family: String,
    val genus: String,
    val genus_id: Int,
    val year: Int,
    val author: String,
    val bibliography: String,
    val rank: String,
    val slug: String,
    val family_common_name: String,
    val scientific_name: String
)

data class Meta(
    val total: Int
)