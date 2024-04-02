package com.ponyu.botanical.domain

import androidx.paging.PagingData
import com.ponyu.botanical.data.remote.plants.PlantData
import com.ponyu.botanical.data.remote.plants.PlantFilterModel
import kotlinx.coroutines.flow.Flow

interface PlantsRepository {
    fun getPlants(plantFilterModel: PlantFilterModel) : Flow<PagingData<PlantData>>
}