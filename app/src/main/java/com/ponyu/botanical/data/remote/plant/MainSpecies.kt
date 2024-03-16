package com.ponyu.botanical.data.remote.plant

import com.google.gson.annotations.SerializedName

data class MainSpecies(
    @SerializedName("id")
    val id: Int,
    @SerializedName("common_name")
    val commonName: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("scientific_name")
    val scientificName: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("bibliography")
    val bibliography: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("rank")
    val rank: String,
    @SerializedName("observations")
    val observations: String,
    @SerializedName("vegetable")
    val vegetable: String,
    @SerializedName("image_url")
    val imageUrl: String,
    /*@SerializedName("duration")
    val duration: array of strings*/
    @SerializedName("edible_part")
    val ediblePart: List<String?>?,
    @SerializedName("images")
    val images: Images,
    @SerializedName("distribution")
    val distribution: Distribution,
    @SerializedName("flower")
    val flower: Flower,
    @SerializedName("foliage")
    val foliage: Foliage,
    @SerializedName("fruit_or_seed")
    val fruitOrSeed: FruitOrSeed,
    @SerializedName("specifications")
    val specifications: Specifications,
    @SerializedName("growth")
    val growth: Growth,
)