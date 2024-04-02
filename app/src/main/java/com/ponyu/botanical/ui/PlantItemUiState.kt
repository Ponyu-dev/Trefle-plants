package com.ponyu.botanical.ui

import com.ponyu.botanical.data.remote.plants.PlantData

data class PlantItemUiState(private val plantData: PlantData) : BaseUiState() {

    fun getId() = plantData.id
    fun getImageUrl() = plantData.imageUrl
    fun getScientificName() = plantData.scientificName
    fun getCommonName() = plantData.commonName
}