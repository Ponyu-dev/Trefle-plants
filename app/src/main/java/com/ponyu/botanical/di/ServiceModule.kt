package com.ponyu.botanical.di

import com.ponyu.botanical.data.remote.plant.PlantRepositoryImpl
import com.ponyu.botanical.data.remote.plants.PlantsRepositoryImpl
import com.ponyu.botanical.domain.PlantRepository
import com.ponyu.botanical.domain.PlantsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun bindPlantsRepository(impl: PlantsRepositoryImpl) : PlantsRepository

    @Binds
    abstract fun bindPlantRepository(impl: PlantRepositoryImpl) : PlantRepository
}