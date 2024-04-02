package com.ponyu.botanical.data.remote.plants

import com.google.gson.annotations.SerializedName

data class PlantsResponse(
    @SerializedName("data")
    val data: List<PlantData>,
    @SerializedName("meta")
    val meta: Meta
)

data class PlantData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("common_name")
    val commonName: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("family")
    val family: String,
    @SerializedName("genus")
    val genus: String,
    @SerializedName("genus_id")
    val genusId: Int,
    @SerializedName("year")
    val year: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("bibliography")
    val bibliography: String,
    @SerializedName("rank")
    val rank: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("family_common_name")
    val familyCommonName: String,
    @SerializedName("scientific_name")
    val scientificName: String
)

data class Meta(
    val total: Int
)