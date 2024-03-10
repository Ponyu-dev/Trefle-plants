package com.ponyu.botanical.fragments.plants

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ponyu.botanical.data.remote.plants.PlantData
import com.ponyu.botanical.domain.PlantsRepository
import com.ponyu.botanical.fragments.plants.adapter.PlantItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPlantsViewModel @Inject constructor(
    private val plantsRepository: PlantsRepository
) : ViewModel() {

    private val _listPlants = MutableStateFlow<List<PlantItem>>(emptyList())
    val listPlants = _listPlants.asStateFlow()

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun load() {
        Log.d(TAG, "init")
        viewModelScope.launch {
            val pairResult = plantsRepository.loadListPlants()

            Log.d(TAG, "result = ${pairResult.first}")

            val plants = pairResult.second
            Log.d(TAG, "result = ${plants?.data?.size}")
            Log.d(TAG, "result = ${plants?.meta?.total}")

            plants?.data?.let { convertDataToListPlantItem(it) }
        }
    }

    private suspend fun convertDataToListPlantItem(data: List<PlantData>){
        _listPlants.emit(data.map {
            PlantItem(it.id, it.scientificName, it.commonName, it.imageUrl)
        })
    }

    companion object {
        private const val TAG = "ListPlantsViewModel"
    }
}