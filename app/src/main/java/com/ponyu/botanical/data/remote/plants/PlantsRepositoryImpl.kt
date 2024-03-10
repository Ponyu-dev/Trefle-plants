package com.ponyu.botanical.data.remote.plants

import com.ponyu.botanical.data.remote.PlantsService
import com.ponyu.botanical.domain.PlantsRepository
import javax.inject.Inject

class PlantsRepositoryImpl @Inject constructor(
    private val service: PlantsService
) : PlantsRepository {
    override suspend fun loadListPlants(): Pair<Boolean, ListPlants?> {
        return try {
            val listPlants = service.getPlants()
            Pair(true, listPlants)
        } catch (e: Exception) {
            Pair(false, null)
        }
    }
}