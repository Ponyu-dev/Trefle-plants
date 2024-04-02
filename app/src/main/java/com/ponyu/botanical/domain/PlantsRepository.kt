package com.ponyu.botanical.domain

import androidx.paging.PagingData
import com.ponyu.botanical.data.remote.plants.PlantData
import kotlinx.coroutines.flow.Flow

interface PlantsRepository {
    fun getPlants() : Flow<PagingData<PlantData>>
}