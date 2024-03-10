package com.ponyu.botanical.domain

import com.ponyu.botanical.data.remote.plants.ListPlants

interface PlantsRepository {
    suspend fun loadListPlants() : Pair<Boolean, ListPlants?>
}