package com.ponyu.botanical.domain

import com.ponyu.botanical.data.remote.plant.PlantInfo

interface PlantRepository {
    suspend fun loadPlant(plantId: Int) : Pair<Boolean, PlantInfo?>
}