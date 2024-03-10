package com.ponyu.botanical.data.remote.plant

import com.ponyu.botanical.data.remote.PlantsService
import com.ponyu.botanical.domain.PlantRepository
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(
    private val service: PlantsService
) : PlantRepository {
    override suspend fun loadPlant(plantId: Int): Pair<Boolean, PlantDetail?> {
        return try {
            val listPlants = service.getPlant(plantId)
            Pair(true, listPlants)
        } catch (e: Exception) {
            Pair(false, null)
        }
    }
}