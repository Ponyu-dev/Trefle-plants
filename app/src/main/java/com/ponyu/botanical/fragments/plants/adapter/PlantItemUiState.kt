package com.ponyu.botanical.fragments.plants.adapter

import com.ponyu.botanical.data.remote.plants.PlantData
import com.ponyu.botanical.ui.BaseUiState

data class PlantItemUiState(private val plantData: PlantData) : BaseUiState() {

    fun getId() = plantData.id
    fun getImageUrl() = plantData.imageUrl
    fun getScientificName() = plantData.scientificName
    fun getCommonName() = plantData.commonName
}